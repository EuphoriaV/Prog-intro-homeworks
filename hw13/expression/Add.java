package expression;

import expression.exceptions.OverflowException;

public class Add extends Operator {
    public Add(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " + ";
    }

    @Override
    public int makeOperation(int first, int second) {
        return first + second;
    }
}
