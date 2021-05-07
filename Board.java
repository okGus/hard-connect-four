
public class Board {
    Coin[][] _board;
    boolean connect_four = true;
    boolean connect_five = false;

    Board() {
        // Default 6x7 - connec four
        _board = new Coin[6][7];
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                _board[r][c] = new Coin();
            }
        }
    }

    Board(int row, int col) {
        _board = new Coin[row][col];
    }

    public void display() {
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                System.out.print(_board[r][c].getColor() + " ");
            }
            System.out.println();
        }
    }

    public void insert(Coin c, int row, int col) {
        _board[row][col].setColor(c.getColor());
    }

    public void five_in_a_row() {
        // connect five, 6x9
        _board = new Coin[6][9];
        connect_five = true;
        connect_four = false;
    }

    public boolean get_connect_four() {
        return connect_four;
    }

    public boolean get_connect_five() {
        return connect_five;
    }
}
