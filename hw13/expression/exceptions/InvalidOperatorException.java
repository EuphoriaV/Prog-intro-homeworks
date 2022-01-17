package expression.exceptions;

public class InvalidOperatorException extends RuntimeException{
    InvalidOperatorException(String s){
        super(s);
    }
}
