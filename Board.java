
public class Board {
    int[][] _board;

    Board() {
        // Default 6x7 - connec four
        _board = new int[6][7];
    }

    Board(int row, int col) {
        _board = new int[row][col];
    }

    public void display() {
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                System.out.print(_board[r][c] + " ");
            }
            System.out.println();
        }
    }
}
