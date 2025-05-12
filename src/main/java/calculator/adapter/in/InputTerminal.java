package calculator.adapter.in;

import calculator.application.port.inport.GetExpressionUserCase;
import camp.nextstep.edu.missionutils.Console;

public class InputTerminal implements GetExpressionUserCase {
    public String getExpression() {
        return Console.readLine();
    }
}
