package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void exampleTest() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void constantExpressionTest() {
        assertSimpleTest(() -> {
            run("5");
            assertThat(output()).contains("결과 : 5");
        });
    }

    @Test
    void customSeparatorTest() {
        assertSimpleTest(() -> {
            run("//;\\n3;4:5");
            assertThat(output()).contains("결과 : 12");
        });
    }

    @Test
    void intOverflowTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("20000000000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void longOverflowTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("50000000000000000000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void severalCustomSeparatorTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//^&\\n3^4&5"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void numberSeparatorTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//3\\n53:43"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void emptyExpressionTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(""))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void intAddingOverflowTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("2000000000:2000000000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void longAddingOverflowTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("5000000000000000000:5000000000000000000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void escapeCharTest() {
        assertSimpleTest(() -> {
            run("//\\\\n3\\4\\5");
            assertThat(output()).contains("결과 : 12");
        });
    }

    @Test
    void emptyValueTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//*\\n3**:,,4"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
