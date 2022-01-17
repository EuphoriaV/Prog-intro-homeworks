import java.io.*;
import java.lang.AutoCloseable;

public class MyScanner implements AutoCloseable {
    private Reader reader;
    private char[] buffer = new char[1000];
    private int pos = 0;
    private int read = 0;
    private Integer integer;
    private String string;
    private String word;

    public MyScanner(Reader reader) {
        this.reader = reader;
    }

    public void close() throws IOException {
        reader.close();
    }

    public int read() throws IOException {
        if (read == -1) {
            return -1;
        }
        if (pos == read) {
            read = reader.read(buffer);
            if (read == -1) {
                return -1;
            } else {
                pos = 0;
            }
        }
        return buffer[pos++];
    }

    public boolean hasNextLine() throws IOException {
        string = nextLine();
        return string != null;
    }

    public boolean hasNextWord() throws IOException {
        word = nextWord();
        return word != null;
    }

    public boolean hasNextInt() throws IOException {
        integer = nextInt();
        return integer != null;
    }

    public boolean hasNextHexInt() throws IOException {
        integer = nextHexInt();
        return integer != null;
    }
    
    public Integer nextInt() throws IOException {
        if (this.integer != null) {
            int answer = integer.intValue();
            this.integer = null;
            return answer;
        }
        int integer;
        int ch;
        StringBuilder stringBuilder = new StringBuilder();
        while ((ch = read()) != -1) {
            if (Character.isLetterOrDigit((char) ch) || (char)ch == '-') {
                stringBuilder.append((char) ch);
            } else if (stringBuilder.length() > 0) {
                integer = Integer.parseInt(stringBuilder.toString());
                return integer;
            }
        }
        if (stringBuilder.length() > 0) {
            integer = Integer.parseInt(stringBuilder.toString());
            return integer;
        } else {
            return null;
        }
    }
    
    public Integer nextHexInt() throws IOException {
        if (this.integer != null) {
            int answer = integer.intValue();
            this.integer = null;
            return answer;
        }
        int integer;
        int ch;
        StringBuilder stringBuilder = new StringBuilder();
        while ((ch = read()) != -1) {
            if (Character.isLetterOrDigit((char) ch)) {
                stringBuilder.append((char) ch);
            } else if (stringBuilder.length() > 0) {
                integer = Integer.parseUnsignedInt(stringBuilder.toString(), 16);
                return integer;
            }
        }
        if (stringBuilder.length() > 0) {
            integer = Integer.parseUnsignedInt(stringBuilder.toString(), 16);
            return integer;
        } else {
            return null;
        }
    }

    public String nextWord() throws IOException {
        if (word != null) {
            String answer = word;
            word = null;
            return answer;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        boolean broken = false;
        while ((ch = read()) != -1) {
            if (!(((char) ch == '\'' || Character.isLetter((char) ch)) || Character.getType((char) ch) == Character.DASH_PUNCTUATION)) {
                broken = true;
                break;
            } else {
                stringBuilder.append((char) ch);
            }
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        } else if(broken){
            return "";
        } else {
            return null;
        }
    }


    public String nextLine() throws IOException {
        if (string != null) {
            String answer = string;
            string = null;
            return answer;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        while ((ch = read()) != -1) {
            stringBuilder.append((char) ch);
            if ((char) ch == '\n') {
                break;
            }
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        } else {
            return null;
        }
    }
}
