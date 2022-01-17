package expression.parser;

import expression.TripleExpression;

@FunctionalInterface
public interface Parser {
    TripleExpression parse(String expression);
}
