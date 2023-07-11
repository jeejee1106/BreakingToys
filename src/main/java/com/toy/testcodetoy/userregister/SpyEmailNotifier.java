package com.toy.testcodetoy.userregister;

/**
 * 스파이(spy)?
 * - 호출된 내역을 기록한다.
 * - 기록한 내용은 테스트 결과를 검증할 때 사용한다.
 * - 스텁이기도 하다.
 *
 * 흠.... 스텁은 기동이 '동작' 하는 것에 초첨을 두고
 * 스파이는 호출 내역을 '기록' 하는 것에 초점을 뒀다고 이해하면 될까?
 */

public class SpyEmailNotifier implements EmailNotifier {

    private boolean called;
    private String email;

    public boolean isCalled() {
        return called;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
