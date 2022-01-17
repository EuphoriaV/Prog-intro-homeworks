package expression;

public class Multiply extends Operator {
    public Multiply(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " * ";
    }

    @Override
    public int makeOperation(int first, int second) {
        return first * second;
    }
}