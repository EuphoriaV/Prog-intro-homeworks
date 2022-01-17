package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.EMPTY, ".",
            Cell.FIRST, "X",
            Cell.SECOND, "0",
            Cell.THIRD, "-",
            Cell.FOURTH, "|"
    );

    private final Cell[][] field;
    private final int m, n, k, number;
    private Cell turn;

    public TicTacToeBoard(int number, int m, int n, int k) {
        this.number = number;
        this.k = k;
        this.m = m;
        this.n = n;
        field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.EMPTY);
        }
        turn = Cell.FIRST;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.RETRY;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin()) {
            switch (turn) {
                case FIRST:
                    return GameResult.WIN1;
                case SECOND:
                    return GameResult.WIN2;
                case THIRD:
                    return GameResult.WIN3;
                case FOURTH:
                    return GameResult.WIN4;
            }
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }
        switch (turn) {
            case FIRST:
                turn = Cell.SECOND;
                break;
            case SECOND:
                if (number == 2) {
                    turn = Cell.FIRST;
                    break;
                }
                turn = Cell.THIRD;
                break;
            case THIRD:
                if (number == 3) {
                    turn = Cell.FIRST;
                    break;
                }
                turn = Cell.FOURTH;
                break;
            case FOURTH:
                turn = Cell.FIRST;
                break;
        }

        return GameResult.UNKNOWN;
    }

    public int getNumber() {
        return number;
    }

    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (field[r][c] == Cell.EMPTY) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return false;
    }

    private boolean checkWin() {
        for (int r = 0; r < m; r++) {
            int count = 0;
            for (int c = 0; c < n; c++) {
                if (field[r][c] == turn) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    return true;
                }
            }
        }
        for (int c = 0; c < n; c++) {
            int count = 0;
            for (int r = 0; r < m; r++) {
                if (field[r][c] == turn) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    return true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (i + j == n) {
                    break;
                }
                if (field[j][j + i] == turn) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    return true;
                }
            }
            count = 0;
            for (int j = 0; j < m; j++) {
                if (i + j == n) {
                    break;
                }
                if (field[m - j - 1][j + i] == turn) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    return true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i + j == m) {
                    break;
                }
                if (field[j + i][j] == turn) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    return true;
                }
            }
            count = 0;
            for (int j = 0; j < n; j++) {
                if (i + j == m) {
                    break;
                }
                if (field[m - i - 1 - j][j] == turn) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.EMPTY
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < n; i++) {
            sb.append((i + 1) + " ");
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < m; r++) {
            sb.append((r + 1) + " ");
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell) + " ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}