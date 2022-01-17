package expression.exceptions;

import expression.*;
public class CheckedMultiply extends Operator {
    public CheckedMultiply(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " * ";
    }

    @Override
    public int makeOperation(int first, int second) {
        if(first!=0 && second!=0 && ((first*second)/first!=second||(first*second)/second!=first)){
            throw new OverflowException("overflow");
        }
        return first * second;
    }
}
