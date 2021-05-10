
public class Board {
    Coin[][] _board;
    boolean connect_four = true;
    boolean connect_five = false;

<<<<<<< HEAD
=======
    /**
     * Initializes a newly created Board object to represent the game state
     */
>>>>>>> main
    Board() {
        // Default 6x7 - connec four
        _board = new Coin[6][7];
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                _board[r][c] = new Coin();
            }
        }
    }

<<<<<<< HEAD
=======
    /**
     * Constructs a new Board object given the row and column of the board size
     * @param row - row of board
     * @param col - column of board
     */
>>>>>>> main
    Board(int row, int col) {
        _board = new Coin[row][col];
    }

<<<<<<< HEAD
=======
    /**
     * Show board state
     */
>>>>>>> main
    public void display() {
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                System.out.print(_board[r][c].getColor() + " ");
            }
            System.out.println();
        }
    }

<<<<<<< HEAD
    // insert new Coin and have update to setColor(getColor)
=======
    /**
     * Insert new Coin object at row and column
     * @param c - Coin object to insert
     * @param row - row of board
     * @param col - column of board
     */
>>>>>>> main
    public void insert(Coin c, int row, int col) {
        _board[row][col] = new Coin();

        update(c, row, col);
    }

<<<<<<< HEAD
=======
    /**
     * Updates the state of Coin object at row and column
     * @param c - Coin object to update
     * @param row - row of board
     * @param col - column of board
     */
>>>>>>> main
    private void update(Coin c, int row, int col) {
        _board[row][col].setColor(c.getColor());
    }

<<<<<<< HEAD
=======
    /**
     * Creates a new Board to play connect five,
     * new size of 6x9
     */
>>>>>>> main
    public void five_in_a_row() {
        // connect five, 6x9
        _board = new Coin[6][9];
        connect_five = true;
        connect_four = false;
    }

<<<<<<< HEAD
=======
    /**
     * Return if we are on default connect four game
     * @return - connect_four
     */
>>>>>>> main
    public boolean get_connect_four() {
        return connect_four;
    }

<<<<<<< HEAD
=======
    /**
     * Return if we are on connect five game
     * @return - connect_five
     */
>>>>>>> main
    public boolean get_connect_five() {
        return connect_five;
    }
}
