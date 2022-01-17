package expression.exceptions;

import expression.TripleExpression;

@FunctionalInterface
public interface Parser {
    TripleExpression parse(String expression) throws /* Change me */ Exception;
}
