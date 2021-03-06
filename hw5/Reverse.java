import java.io.*;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Reader reader = new InputStreamReader(System.in);
        MyScanner scanner = new MyScanner(reader);
        int[][] mas = new int[10][];
        int i = 0;
        String s;
        while (scanner.hasNextLine()) {
	    s = scanner.nextLine();
            mas[i] = getNumbers(s);
            i++;
            if (i == mas.length) {
                mas = Arrays.copyOf(mas, i * 2);
            }
        }
        for (i--; i >= 0; i--) {
            for (int j = mas[i].length - 1; j >= 0; j--) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println();
        }
    	reader.close();
	    scanner.close();
    }

    public static int[] getNumbers(String s) throws IOException {
        int[] mas = new int[5];
        int count = 0;
        Reader reader = new StringReader(s);
        MyScanner scanner = new MyScanner(reader);
        int integer;
        while (scanner.hasNextInt()) {
	    integer = scanner.nextInt();
            mas[count] = integer;
            count++;
            if (count == mas.length) {
                mas = Arrays.copyOf(mas, count * 2);
            }
        }
    	reader.close();
	    scanner.close();
        return Arrays.copyOf(mas, count);
    }
}