package expression.parser;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        skipWhitespase();
        return pos < data.length();
    }

    @Override
    public char next() {
        skipWhitespase();
        return data.charAt(pos++);
    }

    
    
    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }

    private void skipWhitespase() {
        while (pos<data.length() && Character.isWhitespace(data.charAt(pos))) {
            pos++;
        }
    }
}
