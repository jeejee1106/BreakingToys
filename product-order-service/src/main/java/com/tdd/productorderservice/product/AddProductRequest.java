package com.tdd.productorderservice.product;

import org.springframework.util.Assert;

record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
    /*
    레코드 타입은 처음봄.. 공부하자 이것도..ㅜㅜ
     */
    AddProductRequest {
        Assert.hasText(name, "상품명은 필수입니다."); //validation 체크
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
    }
}
