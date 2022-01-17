package expression.exceptions;

public class DivisionByZeroException extends RuntimeException{
    public DivisionByZeroException(String s){
        super(s);
    }
}
