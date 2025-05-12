package calculator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    private final List<Integer> operands;
    private static final String CUSTOM_SEPARATOR_REGEX = "^//(.)\\\\n.*";
    private static final String EXPRESSION_REGEX_BASE = "([0-9]+)[[%s]+([0-9]+)]*";

    public Expression(String expressionLine) {
        operands = new ArrayList<>();

        String separator = "[,:]";
        String expressionBody = expressionLine;

        if (expressionLine.matches(CUSTOM_SEPARATOR_REGEX)) {
            Matcher matcher = Pattern.compile(CUSTOM_SEPARATOR_REGEX).matcher(expressionLine);
            if (matcher.find()) {
                String custom = Pattern.quote(matcher.group(1));
                separator = String.format("[,:%s]", custom);
                expressionBody = expressionLine.substring(5);
            }
        }

        String fullRegex = String.format(EXPRESSION_REGEX_BASE, separator);
        if (!expressionBody.matches(fullRegex)) {
            throw new IllegalArgumentException("잘못된 수식입니다: " + expressionBody);
        }

        String[] tokens = expressionBody.split(separator);
        for (String token : tokens) {
            operands.add(Integer.parseInt(token));
        }
    }

    public int calculate() {
        return operands.stream().reduce(0, Integer::sum);
    }
}
