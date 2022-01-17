package game;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner in;

    public HumanPlayer(Scanner in,int m,int n) {
        super(m,n);
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        return new Move(in.nextInt() - 1, in.nextInt() - 1, position.getTurn());
    }
}