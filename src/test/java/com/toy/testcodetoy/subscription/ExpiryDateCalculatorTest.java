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
    void 첫납부일과_만료일날짜가다를떄_만원납부하면_첫납부일기준으로_다음만료일_정함() {
        // #1
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31)) //이 때부터 구독을 시작했어
                .billingDate(LocalDate.of(2019, 2, 28)) //이 때가 구독 만료일이야
                .payAmount(10000) //그래서 만원을 더 냈어
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31)); //최종 만료일이야

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

    @Test
    void 이만원이상_납부하면_비례해서_만료일계산() {
        // #1 : 이만원 납부했을 때
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2019, 5, 1)
        );

        // #2 : 삼만원 납부했을 때
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        );

        // #3 : 오만원 납부했을 때
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(50000)
                        .build(),
                LocalDate.of(2019, 8, 1)
        );

        // #4 : 구만원 납부했을 때
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(90000)
                        .build(),
                LocalDate.of(2019, 12, 1)
        );

        // #5 : 이만원 납부했을 때
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 11, 1))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2020, 1, 1)
        );

        // #5 : 이만원 납부했을 때
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 11, 30))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2020, 1, 30)
        );
    }

    @Test
    void 첫납부일과_첫만료일날짜가다를때_첫만료일에_다시이만원이상_납부하면_날짜크기비교해야함() {
        /*
        이 부분 좀 헷갈린다..!
        복습할 때 헷갈리면 꼭 책보고 다시 천천히 생각해보기.
         */

        // #1
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31)) //이 때부터 구독을 시작했어
                        .billingDate(LocalDate.of(2019, 2, 28)) //이 때가 만료일이야
                        .payAmount(20000) //그래서 이만원을 더 냈어 (즉, 2019-2-28이 첫 납부일!)
                        .build(),
                LocalDate.of(2019, 4, 30) //그래서 이 때가 최종만료일이야
        );

        // #2
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(40000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        );

        // #3
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2109, 3, 31))
                        .billingDate(LocalDate.of(2019, 4, 30))
                        .payAmount(30000)
                        .build(),
                LocalDate.of(2019, 7, 31)
        );

        // #4
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 12, 31))
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2020, 3, 31)
        );
    }

    @Test
    void 십만원을_납부하면_12개월제공() {
        // #1
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100000)
                        .build(),
                LocalDate.of(2020, 1, 28)
        );
    }

    @Test
    void 십만원이상_납부하면_추가개월제공() {
        // #1
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(110000) //총 13개월을 더 연장한거임
                        .build(),
                LocalDate.of(2020, 2, 28)
        );

        // #2
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 8, 31))
                        .payAmount(110000) //총 13개월을 더 연장한거임
                        .build(),
                LocalDate.of(2020, 9, 30)
        );

        // #3
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 28))
                        .payAmount(200000) //총 22개월을 더 연장한거임
                        .build(),
                LocalDate.of(2021, 3, 28)
        );
    }

    @Test
    void 십만원을_납부했는데_윤달낀2월이라면() {
        // #1
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 2, 29))
                        .payAmount(100000)
                        .build(),
                LocalDate.of(2021, 2, 28)
        );
    }
}
