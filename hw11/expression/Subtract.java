package expression;

public class Subtract extends Operator {
    public Subtract(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " - ";
    }

    @Override
    public int makeOperation(int first, int second) {
        return first - second;
    }
}