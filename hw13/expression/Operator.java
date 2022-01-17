package expression;

import expression.common.Op;

import java.util.Objects;

abstract public class Operator implements MultyExpression{
    final protected MultyExpression first;
    final protected MultyExpression second;
    protected String operation;
    abstract public int makeOperation(int first,int second);
    public Operator(MultyExpression first, MultyExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString(){
        return "(" + first + operation + second + ")";
    }
    
    @Override 
    public int evaluate(int x){
        return makeOperation(first.evaluate(x),second.evaluate(x));
    }
    public int evaluate(int x, int y, int z){
        return makeOperation(first.evaluate(x,y,z),second.evaluate(x,y,z));
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Operator){
            return first.equals(((Operator)o).first) && second.equals(((Operator)o).second) && operation.equals(((Operator)o).operation);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation,second,first);
    }
}
