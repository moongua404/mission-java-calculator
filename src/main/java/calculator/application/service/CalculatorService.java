package calculator.application.service;

import calculator.application.port.inport.GetExpressionUserCase;
import calculator.application.port.outport.ResultTerminal;
import calculator.domain.Expression;
import calculator.domain.enums.MessageConstant;

public class CalculatorService {
    private final ResultTerminal resultTerminal;
    private final GetExpressionUserCase getExpressionUserCase;

    public CalculatorService(ResultTerminal resultTerminal, GetExpressionUserCase getExpressionUserCase) {
        this.resultTerminal = resultTerminal;
        this.getExpressionUserCase = getExpressionUserCase;
    }

    public void run() {
        try {
            resultTerminal.printMessage(MessageConstant.START_GUIDE_MESSAGE);
            String line = getExpressionUserCase.getExpression();
            Expression expression = new Expression(line);
            int result = expression.calculate();
            resultTerminal.printResult(result);
        } catch (RuntimeException e) {
            resultTerminal.handleException(e);
        }
    }
}
