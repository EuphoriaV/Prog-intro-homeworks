import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseTranspose {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int[][] mas = new int[10][];
        int i = 0, maxLength = 0;
        while (sc.hasNextLine()) {
            mas[i] = getNumbers(sc.nextLine());
            if (mas[i].length > maxLength) {
                maxLength = mas[i].length;
            }
            i++;
            if (i == mas.length) {
                mas = Arrays.copyOf(mas, i * 2);
            }
        }
        int n = Integer.max(i, maxLength);
        mas = Arrays.copyOf(mas, i);
        for (i = 0; i < maxLength; i++) {
            for (int j = 0; j < mas.length; j++) {
                if (mas[j] != null) {
                    if (mas[j].length > i) {
                        System.out.print(mas[j][i] + " ");
                    }
                }
            }
            System.out.println();
        }
        sc.close();
    }

    public static int[] getNumbers(String s) throws IOException {
        int[] mas = new int[5];
        int count = 0;
        Scanner sc = new Scanner(s);
        while (sc.hasNextInt()) {
            mas[count] = sc.nextInt();
            count++;
            if (count == mas.length) {
                mas = Arrays.copyOf(mas, count * 2);
            }
        }
        return Arrays.copyOf(mas, count);
    }
}