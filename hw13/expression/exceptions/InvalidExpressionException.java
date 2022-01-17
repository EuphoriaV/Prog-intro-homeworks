package expression.exceptions;

public class InvalidExpressionException extends RuntimeException{
    InvalidExpressionException(String s){
        super(s);
    }
}
