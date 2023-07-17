package com.toy.testcodetoy.passwordmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();

    //각각의 테스트 메서드에 있던 중복된 코드들을 이렇게 따로 메서드로 꺼냈다.
    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = passwordStrengthMeter.meter(password);
        Assertions.assertEquals(expStr, result);
    }

    @Test
    void name() {
        //이렇게 비어있는 테스트 코드를 처음에 작성해서 돌려보는것도
        //테스트 환경이 제대로 갖춰져 있는지 알 수 있기 때문에 유용하게 쓰일수 있다.
    }

    @Test
    @DisplayName("모든 조건 충족 시 강도 강함")
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!2AB", PasswordStrength.STRONG);
        assertStrength("abc!Add9", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이가 8자 미만이고, 나머지 조건은 충족할 때 강도 보통")
    void meetsOtherCriteria_except_For_Length_Then_Normal() {
        assertStrength("ab12!A", PasswordStrength.NORMAL);
        assertStrength("abc!A2d", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고, 나머지 조건은 충족할 때 강도 보통")
    void meetsOtherCriteria_except_For_Number_Then_Normal() {
        assertStrength("adadad!A", PasswordStrength.NORMAL);
        assertStrength("abc!Adkkkk", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("값이 널일 때 INVALID")
    void nullInputThenInvalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("값이 빈값일 때 INVALID")
    void emptyInputThenInvalid() {
        assertStrength("", PasswordStrength.INVALID);
        assertStrength(" ", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고, 나머지 조건은 충족할 때 강도 보통")
    void meetsOtherCriteriaExceptForUppercaseThenNormal() {
        assertStrength("abcdefg123", PasswordStrength.NORMAL);
        assertStrength("0606toytoy", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8자 이상인 조건만 충족할 경우 강도 약함")
    void meetsOnlyLengthCriteriaThenWeak() {
        assertStrength("aaaaaaaaaa", PasswordStrength.WEAK);
        assertStrength("toytoytoytoy", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족할 경우 강도 약함")
    void meetsOnlyNumCriteriaThenWeak() {
        assertStrength("12345", PasswordStrength.WEAK);
        assertStrength("1234567", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족할 경우 강도 약함")
    void meetsOnlyUppercaseCriteriaThenWeak() {
        assertStrength("ABCDEFG", PasswordStrength.WEAK);
        assertStrength("ZZZZZaa", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않는 경우")
    void meetsNoCriteriaThenWeak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
