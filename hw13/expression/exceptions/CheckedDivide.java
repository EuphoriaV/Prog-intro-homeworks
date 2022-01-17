package expression.exceptions;
import expression.*;
public class CheckedDivide extends Operator {
    public CheckedDivide(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " / ";
    }

    @Override
    public int makeOperation(int first, int second) {
        if(first == Integer.MIN_VALUE && second == -1){
            throw new OverflowException("overflow");
        }
        if(second == 0){
            throw new DivisionByZeroException("division by zero");
        }
        return first / second;
    }
}
