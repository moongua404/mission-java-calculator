package calculator;

import calculator.domain.Expression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void 기본_구분자_쉼표_콜론으로_계산() {
        Expression expr = new Expression("1,2:3");
        assertEquals(6, expr.calculate());
    }

    @Test
    void 커스텀_구분자_세미콜론으로_계산() {
        Expression expr = new Expression("//;\\n1;2;3");
        assertEquals(6, expr.calculate());
    }

    @Test
    void 커스텀_구분자_점으로_계산() {
        Expression expr = new Expression("//.\\n4.5:6");
        assertEquals(15, expr.calculate());
    }

    @Test
    void 커스텀_구분자와_기본_구분자_혼합() {
        Expression expr = new Expression("//|\\n7|8,9:10");
        assertEquals(34, expr.calculate());
    }

    @Test
    void 유효하지_않은_표현식_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expression("1,,2:3");
        });
    }

    @Test
    void 커스텀_정의된_구분자가_아닌_문자_포함시_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expression("//;\\n1;2-3");
        });
    }

    @Test
    void 숫자가_아닌_문자가_포함되면_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Expression("1,a:3");
        });
    }

    @Test
    void 한자리_숫자가_아닌_수() {
        Expression expr = new Expression("1,10,100,3000");
        assertEquals(3111, expr.calculate());
    }
}
