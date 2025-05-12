package calculator.domain.enums;

public enum MessageConstant {
    START_GUIDE_MESSAGE("덧셈할 문자열을 입력해 주세요."),
    RESULT_MESSAGE("결과 : ");

    private String message;

    MessageConstant(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
