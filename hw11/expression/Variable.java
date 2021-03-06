package expression;

import java.util.Objects;

public class Variable implements MultyExpression {
    String str;

    public Variable(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }
    
    @Override
    public int evaluate(int x, int y, int z){
        switch (str){
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Variable){
            return str == ((Variable)o).str;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
