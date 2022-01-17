package expression.exceptions;

import expression.*;
public class CheckedNegate implements MultyExpression {
    MultyExpression first;

    public CheckedNegate(MultyExpression exp) {
        first = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "-(" + first + ")";
    }

    public int makeOperation(int first){
        if(first == Integer.MIN_VALUE){
            throw new OverflowException("overflow");
        }
        return -first;
    }

    @Override
    public int evaluate(int x) {
        return makeOperation(first.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(first.evaluate(x, y, z));
    }
}
