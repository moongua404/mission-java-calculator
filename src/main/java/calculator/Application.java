package calculator;

import calculator.adapter.in.InputTerminal;
import calculator.adapter.out.OutputTerminal;
import calculator.application.service.CalculatorService;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        CalculatorService calculatorService = new CalculatorService(new OutputTerminal(), new InputTerminal());
        calculatorService.run();
    }
}
