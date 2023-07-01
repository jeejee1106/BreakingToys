package com.toy.testcodetoy.subscription;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int payAmount = payData.getPayAmount(); //납부금액
        int addedMonth = payAmount == 100000 ? 12 : payAmount / 10000;

        if (payData.getFirstBillingDate() != null) {
            //첫 납부일이 있으면 == 첫 만료일에 돈을 더 내면
            return expiryDateUsingFirstBillingDate(payData, addedMonth);
        } else {
            return payData.getBillingDate().plusMonths(addedMonth);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonth) {

        LocalDate candidateExp        = payData.getBillingDate().plusMonths(addedMonth); //후보만료일
        int dayLengthOfCandidateMonth = YearMonth.from(candidateExp).lengthOfMonth(); //후보만료일이 있는 달의 마지막날짜
        int dayOfFirstBilling         = payData.getFirstBillingDate().getDayOfMonth(); //첫 납부일(만료일) 일자

        //첫 납부일의 일자와 후보 만료일의 일자가 다르면
        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            //후보만료일이 포함된 달의 마지막날보다 첫 납부일의 일자가 크면
            if (dayLengthOfCandidateMonth < dayOfFirstBilling) {
                //후보만료일을 그 달(후보만료일이 포함된 달)의 마지막 날로 조정해야한다.
                return candidateExp.withDayOfMonth(dayLengthOfCandidateMonth);
            }
            //첫 납부일의 일자를 후보 만료일의 일자로 사용
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

}
