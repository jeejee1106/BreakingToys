package com.toy.testcodetoy.userregister;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

/**
 * 모의(mock)?
 * - '기대한 대로 상호작용하는지' 행위를 검증한다.
 * - 기대한 대로 동작하지 않으면 익셉션을 발생할 수 있다.
 * - 모의 객체는 스텁이자 스파이도 된다.
 */

@SpringBootTest
public class UserRegisterMockTest {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class); //WeakPasswordChecker 타입의 모의 객체를 생성
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class); //EmailNotifier 타입의 모의 객체를 생성

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        /*
            BDDMockito?
            - 모의 객체에 스텁을 구성할 수 있다.
            - given() : 모의 객체의 메서드가 특정 값을 리턴하도록 설정할 수 있다.
            - willReturn() : '특정 값을 리턴하도록'의 특정 값 설정.
         */
        BDDMockito
                //"pw"인자를 사용해서 모의 객체의 checkPasswordWeak() 메서드를 호출하면
                .given(mockPasswordChecker.checkPasswordWeak("pw"))
                //결과로 true를 리턴하라
                .willReturn(true);

        Assertions.assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입 시 암호 검사 수행 했는지 확인")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito
                //호출 여부를 검증할 인자(모의객체, mockPasswordChecker) 전달
                .then(mockPasswordChecker)
                //특정 메서드가 호출됐는지 검증하는데
                .should()
                //임의의 String 타입 인자를 이용해서 checkPasswordWeak() 메서드 호출 여부를 확인한다.
                //실제로 불려야 할 메서드 지정
                .checkPasswordWeak(BDDMockito.anyString()); //정확한 값이 아니라 호출여부가 중요하다면 any~() 등을 사용한다.
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        //캡쳐 기능을 사용할 것이다.
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        BDDMockito
                .then(mockEmailNotifier)
                .should() //mockEmailNotifier가 호출됐는지 확인
                .sendRegisterEmail(captor.capture()); //호출할 때 전달한 인자가 ArgumentCaptor에 담긴다.

        String realEmail = captor.getValue(); //getValue() 메서드를 사용하여 보관한 인자를 구할 수 있다.
        Assertions.assertEquals("email@email.com", realEmail);

    }

}
