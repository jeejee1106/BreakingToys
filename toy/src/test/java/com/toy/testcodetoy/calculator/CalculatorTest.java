package com.toy.testcodetoy.calculator;

import com.toy.testcodetoy.calculator.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        Assertions.assertEquals(3, result); //기대하는 값, 실제값
        Assertions.assertEquals(5, Calculator.plus(4, 1)); //기대하는 값, 실제값
    }

}
