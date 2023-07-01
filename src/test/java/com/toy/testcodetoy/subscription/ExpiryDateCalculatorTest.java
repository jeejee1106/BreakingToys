package com.toy.testcodetoy.subscription;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ExpiryDateCalculatorTest {

    /*
    쉬운거?
    음,... 1만원 내면  한달 뒤 서비스 만료

    - 1만원을 냈는지 확인?
    - 서비스가 만료가 됐는지 안됐는지 테스트?

    - 몇개웚치를 냈는지 확인
    - 10만원 이상 냈으면 12개월 서비스 제공 -> 12개월 뒤 만료인지 테스트
     */

    private void assertExpiryDate(PayData payData, LocalDate expectExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        Assertions.assertEquals(expectExpiryDate, realExpiryDate);
    }


    @Test
    void 만원납부하면_한달뒤가_만료일이됨() {
        // #1
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        );

//        LocalDate billingDate = LocalDate.of(2019, 3, 1); //만원 납부한 날
//        int payAmount = 10000;
//
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//
//        Assertions.assertEquals(LocalDate.of(2019, 4, 1), expiryDate);

        // #2
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 6, 5))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 7, 5)
        );

//        LocalDate billingDate2 = LocalDate.of(2019, 6, 5); //만원 납부한 날
//        int payAmount2 = 10000;
//
//        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
//        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);
//
//        Assertions.assertEquals(LocalDate.of(2019, 7, 5), expiryDate2);
    }

    @Test
    void 납부일과_한달뒤_일자가_같지않음() {
        // #1 - 따로 로직을 구현하지 않아도 plusMonths() 메서드가 자동으로 한달을 구해줌
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 2, 28)
        );

        // #2
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        );

        // #3
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2020, 2, 29)
        );
    }

    @Test
    void 첫납부일과_만료일날짜가다를떄_만원납부하면_첫납부일기준으로_다음만료일정함() {
        // #1
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        // #2
        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        // #3
        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }
}
