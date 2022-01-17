package expression.exceptions;

public class StringSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    public boolean hasNext() {
        skipWhitespase();
        return pos < data.length();
    }
    
    public char next() {
        skipWhitespase();
        return data.charAt(pos++);
    }


    public boolean hasNext1() {
        return pos < data.length();
    }

    public char next1() {
        return data.charAt(pos++);
    }

    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }

    public void skipWhitespase() {
        while (pos<data.length() && Character.isWhitespace(data.charAt(pos))) {
            pos++;
        }
    }
}
