package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input m:");
        int m = -1, n = -1, k = -1;
        while (m <= 0) {
            System.out.println("m must be positive int");
            m = sc.nextInt();
        }
        System.out.println("Input n:");
        while (n <= 0) {
            System.out.println("n must be positive");
            n = sc.nextInt();
        }
        System.out.println("Input k:");
        while (k <= 0 || k > m || k > n) {
            System.out.println("k must be positive and less or equal than m and n");
            k = sc.nextInt();
        }
        ArrayList<Player> mas = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "R":
                    mas.add(new RandomPlayer(m, n));
                    break;
                case "H":
                    mas.add(new HumanPlayer(sc, m, n));
                    break;
                case "C":
                    mas.add(new CheatingPlayer(m, n));
                    break;
                case "S":
                    mas.add(new SequentialPlayer(m, n));
                    break;
            }
        }
        final int result = new TwoPlayerGame(new TicTacToeBoard(mas.size(), m, n, k), mas).play(true);
        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 3:
                System.out.println("Third player won");
                break;
            case 4:
                System.out.println("Fourth player won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
    }
}