package game;

import java.util.Random;

public class RandomPlayer extends Player {
    private final Random random = new Random();

    public RandomPlayer(int m, int n){
        super(m,n);
    }
    
    @Override
    public Move makeMove(Position position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(m),
                    random.nextInt(n),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
