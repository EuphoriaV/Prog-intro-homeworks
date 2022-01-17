package game;

public abstract class Player {
    protected int n, m;

    protected Player(int m, int n) {
        this.m = m;
        this.n = n;
    }

    abstract Move makeMove(Position position);
}