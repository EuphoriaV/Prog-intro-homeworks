package expression;

public class Const implements MultyExpression {
    private int integer;

    public Const(int integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return integer + "";
    }

    @Override
    public int evaluate(int x) {
        return integer;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return integer;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Const){
            return integer == ((Const)o).integer;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return integer;
    }
}
