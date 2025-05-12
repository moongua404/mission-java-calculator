package calculator.application.port.outport;

import calculator.domain.enums.MessageConstant;

public interface ResultTerminal {
    void printMessage(MessageConstant message);
    void printResult(int result);
    void handleException(RuntimeException e);
}
