package com.toy.testcodetoy.userregister;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRegisterTest {

    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {

        /*
            - 실제 동작하는 구현은 필요하지 않다.
            - 약한 암호인지 여부를 알려주기만 하면 되므로 스텁 대역이면 충분하다.
         */
        //스텁으로 하여금 암호 확인 요청이 오면 암호가 약하다고 응답하도록 설정
        //아직 구현 전이니까 그런 목적으로 작성한 코드임!
        stubPasswordChecker.setWeak(true);

        Assertions.assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists() {
        //이미 같은 ID가 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        Assertions.assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("같은 ID가 존재하지 않으면 가입 성공")
    @Test
    void noDupId_RegisterSuccess() {

        userRegister.register("id", "pw", "email");

        User saveUser = fakeRepository.findById("id"); //가입결과 확인

        Assertions.assertEquals("id", saveUser.getId());
        Assertions.assertEquals("email", saveUser.getEmail());
    }
}
