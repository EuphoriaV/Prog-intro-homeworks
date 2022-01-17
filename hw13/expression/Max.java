package expression;

public class Max extends Operator {
    public Max(MultyExpression f, MultyExpression s) {
        super(f, s);
        operation = " max ";
    }

    @Override
    public int makeOperation(int first, int second) {
        return first > second ? first : second;
    }
}
