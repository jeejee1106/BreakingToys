package com.toy.testcodetoy.subscription;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = 1;
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonth); //후보만료일

        if (payData.getFirstBillingDate() != null) {
            int firstBillingDayOfMonth = payData.getFirstBillingDate().getDayOfMonth();

            //첫 납부일의 일자와 후보 만료일의 일자가 다르면
            if (firstBillingDayOfMonth != candidateExp.getDayOfMonth()) {
                //첫 납부일의 일자를 후보 만료일의 일자로 사용
                return candidateExp.withDayOfMonth(firstBillingDayOfMonth);
            }
        }
        return candidateExp;
    }

}
