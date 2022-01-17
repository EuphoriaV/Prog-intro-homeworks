package expression;

public class Min extends Operator {
    public Min(MultyExpression f, MultyExpression s) {
        super(f, s);
        operation = " min ";
    }

    @Override
    public int makeOperation(int first, int second) {
        return first < second ? first : second;
    }
}