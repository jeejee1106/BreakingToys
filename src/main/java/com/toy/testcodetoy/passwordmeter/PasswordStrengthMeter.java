package com.toy.testcodetoy.passwordmeter;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            return PasswordStrength.INVALID;
        }

        //if문 안에 조건을 직접 넣으면 이 변수들도 제거 가능하지만, 난 좀더 명확하게 조건명으로 작성하는 것을
        //더 선호하기 때문에 이 변수들은 놔두도록 하겠다.
        boolean hasLengthEnough = s.length() >= 8;
        boolean hasContainsNum  = meetsContainingNumberCriteria(s);
        boolean hasContainsUpp  = meetsContainingUppercaseCriteria(s);
        int metCounts = getMetCounts(hasLengthEnough, hasContainsNum, hasContainsUpp);

        if (metCounts <= 1) {return PasswordStrength.WEAK;}
        if (metCounts == 2) {return PasswordStrength.NORMAL;}
        return PasswordStrength.STRONG;

        //아래 코드들을 리팩토링한 결과가 위 코드이다!
        /*
        if (hasLengthEnough && !hasContainsNum && !hasContainsUpp) {
            return PasswordStrength.WEAK;
        }

        if (!hasLengthEnough && hasContainsNum && !hasContainsUpp) {
            return PasswordStrength.WEAK;
        }

        if (!hasLengthEnough && !hasContainsNum && hasContainsUpp) {
            return PasswordStrength.WEAK;
        }
        */

        /*
        if (!hasLengthEnough) {
            return PasswordStrength.NORMAL;
        }

        if (!hasContainsNum) {
            return PasswordStrength.NORMAL;
        }

        if (!hasContainsUpp) {
            return PasswordStrength.NORMAL;
        }
         */

    }

    private int getMetCounts(boolean hasLengthEnough, boolean hasContainsNum, boolean hasContainsUpp) {
        int metCounts = 0;

        if (hasLengthEnough) {metCounts++;}
        if (hasContainsNum) {metCounts++;}
        if (hasContainsUpp) {metCounts++;}

        return metCounts;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }
}
