public class Board {
    private Boolean winOrNot;
    private Coin[][] _board;
    private boolean connect_four = true;
    private boolean connect_five = false;
    private int currHor, currVer, boardLength, boardHeight, seqNum;

	/**
	 * Initializes new board state
	 */
    Board() {
        // Default 8x8 - connect four
        boardLength = 8;
        boardHeight = 8;
		_board = new Coin[boardHeight][boardLength];
        seqNum = 4;
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                _board[r][c] = new Coin();
            }
        }
    }

	/**
	 * Initializes board state given row and column
	 * @param row - row
	 * @param col - column
	 */
    Board(int row, int col) {
        _board = new Coin[row][col];
    }

	/**
	 * Displays everything in board
	 */
    public void display() {
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                System.out.print(r + "," + c + _board[r][c].getColor() + " ");
            }
            System.out.println();
        }
    }

    // insert new Coin and have update to setColor(getColor)
	/**
	 * Inserts Coin object on row and column
	 * @param c - Coin
	 * @param row - row
	 * @param col - column
	 */
    public void insert(Coin c, int row, int col) {
        if(_board[row][col] != null)
        {
            System.out.println("There are something here.");
            return;
        }

        boolean hasCoinUnder = false;

        while(!hasCoinUnder  && row + 1 < _board.length)
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
        System.out.println("Checking over in insert");
        update(c, row, col);
    }

	/**
	 * Updates inserted Coin
	 * @param c - Coin to insert
	 * @param row - row 
	 * @param col - column
	 */
    private void update(Coin c, int row, int col) {
        _board[row][col].setColor(c.getColor());
        System.out.println("Checking over in update");
        boolean w = gameOver(this, c, row, col);
    }

	/**
	 * Changes from connect four state to connect five
	 */
    public void five_in_a_row() {
        // connect five, 6x9
        _board = new Coin[6][9];
        boardLength = 9;
        boardHeight = 6;
        seqNum = 5;
        connect_five = true;
        connect_four = false;
    }

	/**
	 * Returns if we are on connect four
	 * @return - connect_four
	 */
    public boolean get_connect_four() {
        return connect_four;
    }

	/**
	 * Returns if we are on connect five
	 * @return - connect_five
	 */
    public boolean get_connect_five() {
        return connect_five;
    }
    
    /**
     * Returns board state
     * @return - _board
     */
    public Coin[][] getBoard()
    {
    	return _board;
    }
    
    /**
     * Checks whether it is in a sequence of connect 4 or 5
     */
    public boolean gameOver(Board _board, Coin c, int row, int col) 
	{
		boolean isOver = false;	//win check used for this whole method
		String currColor = c.getColor();	//player color
		Coin[][] currBoard = _board.getBoard();		//duplicates the board into a new board
		int inARow = 0;	//keeping track of the amount of the same color in a row
		
		//In theory we would only need to check a few directions for the end of a sequence, because if the piece itself 
		//is already the tail then you could go the other directions to check for a victory
		//you never need to check for vertical aka 8, because a new piece will always be on top
		//so you would only need to check down left, down right, and horizontal

		//Direction is correlating the numpad 7 8 9
		//   								  4 5 6
		//  								  1 2 3
		
		//I am attempting to get to the leftmost digit from each of these, checking each direction and then going from there
		
		
		//firstly going to just check the vertical win condition

		//if it is already the tail in ANY situation, then you only need to scan 4 directions, downwards, down and to the right, to the right, and upwards and to the right
		
		if(!isOver)//horizontal 4-6
		{
			inARow = 0;
			giveTail(currBoard, row, col - 1, currColor, 4);	inARow = 0;
			for(int i = currHor; i < boardLength; i++)
			{
				if(currBoard[row][i].getColor() == currColor)
				{
					inARow++;
					if(inARow == seqNum)
					{
						isOver = true;
						break;
					}
				}
				else//if next position downwards is not same color, breaks the loop and determines no vertical victory
				{
					inARow = 0;
					break;
				}
			}
		}
		if(row <= boardHeight - 4)
		{
			if(!isOver)//if it's too small to get a vertical win just don't check
			{
				inARow = 0;
				for(int i = row; i < boardHeight; i++)//no need to get to the tail end, since vertical win condition would mean this would be on top anyways 8-2
				{
					if(currBoard[i][col].getColor() == currColor)
					{
						inARow++;
						if(inARow == seqNum)
						{
							isOver = true;
							break;
						}
					}
					else//if next position downwards is not same color, breaks the loop and determines no vertical victory
					{
						inARow = 0;
						break;
					}
				}
			}
			if(!isOver)//upleft scan, for down right victory scan 7-3
			{
				inARow = 0;
				giveTail(currBoard, row - 1, col - 1, currColor, 7);
				boolean isBroke = false;
				int i = currVer - 1, j = currHor - 1;
				//initially made the mistake of using a for loop here, i cannot do that because it would iterate through everything between
				do {
					if(i >= boardHeight - 1 || j > boardLength - 1)//out of bounds
					{
						isBroke = true;
					}
					if(currBoard[i][j].getColor() == currColor || !isBroke)
					{						
						inARow++;
					}
					if(currBoard[i][j].getColor() != currColor)//Then end checks, basically if either they win or there is a break in the pattern then it will trigger this block
					{						
						isBroke = true;
						inARow = 0;
					}
					if(inARow == seqNum)
					{						
						isOver = true;
						isBroke = true;
					}
					i++;
					j++;
				}while(!isBroke);
			}
			if(!isOver)//upright scan, for up right victory scan 1-9
			{	
				inARow = 0;
				giveTail(currBoard, row + 1, col - 1, currColor, 1);
				boolean isBroke = false;
				//initially made the mistake of using a for loop here, i cannot do that because it would iterate through everything between
				do {
					int i = currVer, j = currHor;
					
					if(currBoard[i][j].getColor() == currColor)
					{
						inARow++;
					}
					if(currBoard[i][j].getColor() != currColor)//Then end checks, basically if either they win or there is a break in the pattern then it will trigger this block
					{
						isBroke = true;
						inARow = 0;
					}
					if(inARow == seqNum)
					{
						isOver = true;
						isBroke = true;
					}
					i--;
					j++;
				}while(!isBroke);
			}	
		}
		return isOver; //replace with isOver before done
	}
	
    /**
     * Navigates to the end of a sequence of coins
     */
	public void giveTail(Coin[][] c, int row, int col, String currColor, int direction)
	{
		boolean preemptiveTest;
		boolean inBounds = (row < boardHeight && col < boardLength && col >= 0 && row >= 0);
		if(inBounds)
		{
			preemptiveTest = (c[row][col].getColor() == currColor);//since the call in the other code is going to preemptively adjust the digits you can make this call in advance and declare a variable
		}
		else
		{
			preemptiveTest = false;
		}
		switch(direction) 
		{
		case 7:
			if(!inBounds||!preemptiveTest)//cannot go left or cannot go higher
			{
				currHor = col + 1;
				currVer = row + 1;
				break;
			}//reverting the change made by the recursion and then breaking out of the switch case statement and thus the recursion
			if(preemptiveTest)	//checking if most top left piece of the player color, will recurse until it is
			{
				giveTail(c, row - 1, col - 1, currColor, direction);
			}
			break;
		case 4:
			if(!inBounds||!preemptiveTest)
			{
				currVer = row;
				currHor = col + 1;
			}
			if(preemptiveTest)//checking for leftmost
			{
				giveTail(c, row, col - 1, currColor, direction);
			}
			break;
		case 1:
			if(!inBounds||!preemptiveTest)
			{
				currVer = row - 1;
				currHor = col + 1;
			}
			if(preemptiveTest)//checking for leftmost and downmost
			{
				giveTail(c, row + 1, col - 1, currColor, direction);
			}
			break;
		}
	}
}
