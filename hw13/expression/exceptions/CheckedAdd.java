package expression.exceptions;

import expression.*;

public class CheckedAdd extends Operator {
    public CheckedAdd(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " + ";
    }

    @Override
    public int makeOperation(int first, int second) {
        if((first>0 && second>0 && first+second<=0)||(first<0 && second<0 && first+second>=0)){
            throw new OverflowException("overflow");
        }
        return first + second;
    }
}
