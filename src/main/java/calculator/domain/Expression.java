package calculator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Expression {
    private final List<Integer> operands;
    private static final String CUSTOM_SEPARATOR_REGEX = "^//(.)\\\\n(.*)";
    private static final String EXPRESSION_REGEX_BASE = "([0-9]+)[[%s]+([0-9]+)]*";

    public Expression(String expressionLine) {
        operands = new ArrayList<>();

        String separator = "[,:]";
        String expressionBody = expressionLine;

        Matcher matcher = Pattern.compile(CUSTOM_SEPARATOR_REGEX).matcher(expressionBody);
        if (matcher.find()) {
            separator = String.format("[,:%s]", Pattern.quote(matcher.group(1)));
            expressionBody = matcher.group(2);
        }

        validateExpression(expressionBody, separator);
        parseOperand(expressionBody, separator);
    }

    private void validateExpression(String expressionBody, String separator) {
        String fullRegex = String.format(EXPRESSION_REGEX_BASE, separator);
        if (!expressionBody.matches(fullRegex)) {
            throw new IllegalArgumentException("잘못된 수식입니다: " + expressionBody);
        }
    }

    private void parseOperand(String body, String separatorRegex) {
        Stream.of(body.split(separatorRegex))
                .map(Integer::parseInt)
                .forEach(operands::add);
    }


    public int calculate() {
        return operands.stream().reduce(0, Math::addExact);
    }
}
