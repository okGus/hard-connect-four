
public class Board {
	Coin[][] _board;
    boolean connect_four = true;
    boolean connect_five = false;

    /**
     * Initializes a newly created Board object to represent the game state
     */
    Board() {
        // Default 6x7 - connec four
        _board = new Coin[6][7];
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                _board[r][c] = new Coin();
            }
        }
    }

    /**
     * Constructs a new Board object given the row and column of the board size
     * @param row - row of board
     * @param col - column of board
     */
    Board(int row, int col) {
        _board = new Coin[row][col];
    }

    /**
     * Show board state
     */
    public void display() {
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                System.out.print(_board[r][c].getColor() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Insert new Coin object at row and column,the range of row and col need to be control at runner class
     * Check if there is coin at current position, shows error and stop if there is
     * Check if there is coin under, will move down if not(simulate falling)
     * @param c - Coin object to insert
     * @param row - row of board
     * @param col - column of board
     */
    public void insert(Coin c, int row, int col) {
    	boolean hasCoinUnder = false;
        if(_board[row][col] != null)
        {
        	System.out.println("There are something here.");
        	return;
        }
        
        while(!hasCoinUnder && row + 1 < _board.length)
        {
        	if(_board[row+1][col] != null)
        	{
        		hasCoinUnder = true;
        	}
        	else
        	{
        		row++;
        	}
        }
    	_board[row][col] = new Coin();
        
        update(c, row, col);
    }

    /**
     * Updates the state of Coin object at row and column
     * @param c - Coin object to update
     * @param row - row of board
     * @param col - column of board
     */
    private void update(Coin c, int row, int col) {
        _board[row][col].setColor(c.getColor());
    }

    /**
     * Creates a new Board to play connect five,
     * new size of 6x9
     */
    public void five_in_a_row() {
        // connect five, 6x9
        _board = new Coin[6][9];
        connect_five = true;
        connect_four = false;
    }

    /**
     * Return if we are on default connect four game
     * @return - connect_four
     */
    public boolean get_connect_four() {
        return connect_four;
    }

    /**
     * Return if we are on connect five game
     * @return - connect_five
     */
    public boolean get_connect_five() {
        return connect_five;
    }
}
