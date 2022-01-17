package expression;

public class Divide extends Operator {
    public Divide(MultyExpression first, MultyExpression second) {
        super(first, second);
        operation = " / ";
    }

    @Override
    public int makeOperation(int first, int second) {
        return first / second;
    }
}