package expression;

public class t0 implements MultyExpression {

    MultyExpression first;

    public t0(MultyExpression exp) {
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
        return "t0(" + first + ")";
    }

    public int makeOperation(int first) {
        return Integer.numberOfTrailingZeros(first);
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
