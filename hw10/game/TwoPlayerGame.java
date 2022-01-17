package game;

import java.util.ArrayList;

public class TwoPlayerGame {
    private final Board board;
    private final ArrayList<Player> players;

    public TwoPlayerGame(Board board, ArrayList<Player> players) {
        this.board = board;
        this.players = players;
    }

    public int play(boolean log) {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                int result = makeMove(players.get(i), i + 1, log);
                while (result == -2) {
                    System.out.println("Invalid move");
                    result = makeMove(players.get(i), i + 1, log);
                }
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN1:
            case WIN3:
            case WIN2:
            case WIN4:
                return no;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            case RETRY:
                return -2;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
