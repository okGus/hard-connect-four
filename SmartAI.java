import java.lang.Math;
import java.util.ArrayList;
//This is a smart AI that use the max algorithm to decide where to insert the coin
//@Param = Board for changing data inside

public class SmartAI extends Player implements AiInterface{
	//aiWin,playerWin,tie = condition bools for terminal method
	//scoreArray = store each determining score of the moves at the position for searching the coordinate of the best move later
	private boolean aiWin = false;
	private boolean playerWin = false;
	private boolean tie = false;
	private int[][] scoreArray = new int[8][8];
	//super constructor
	public SmartAI(String color)
	{
		super(color);
	}
	//sct = 
	public Board act(Board b)
	{
		//initialize all value in  the scoreArray to a very low number
		for(int q = 0;q < 8 ;q++)
		{
			for(int w = 0; w < scoreArray.length;w++)
			{
				scoreArray[q][w] = -10000000;
			}
		}
		//create a board that is the same as board from game for calculating
		Board sb = b;
		//create a new coin which represent AI coin
		Coin c = new Coin(color);
		//depth = moves predicted by computer
		//alpha, beta = object for storing computing score in minimax method 
		int depth = 5;
		int alpha = 10000000;
		int beta = -10000000;
		//maximizingPlayer = bool for identifying which recursion method to call, min or max 
		boolean maximizingPlayer = true;
		//call the minimax
		int score = minimax(sb, depth, alpha, beta, maximizingPlayer);
		//loop through the scoreArry to find which Score is fitting the score returned by the minimax
		//that would be the position to insert the coin, so call insert
		for (int i = 0; i < 8; i++) 
		{ 
            for (int j = 0; j < 8; j++) 
            {
                if (scoreArray[i][j] == score) 
                {
                    b.insert(c,i,j);
                }
            }
        }

		return b;
		
	}
	
	//this method is to find the available position to place a Coin
	//available = can be placed(has coin under or at the last row of the board)
	//store the info of the position and pass back for the minimax method to use
	//@param = the board passed from the game
	public ArrayList<Point> getAvailableLoc(Board b)
	{
		Coin[][] c = b.getBoard();
		ArrayList<Point> ret = new ArrayList<Point>();
		for(int row = 0; row < c.length;row++)
		{
			for(int col = 0; col < c[row].length;col++)
			{
				if((c[row][col] == null) && (row == c.length-1 || c[row+1][col] != null))
				{
					Point i = new Point(row,col);
					ret.add(i);
				}
			}
		}
		return ret;
	}
	
	//This method is to simulating player and ai's future moves and use a rating system to find the best move for ai
	public int minimax(Board b, int depth, int alpha, int beta, Boolean maximizingPlayer)
	{
		//make a new board and get 2darray inside for evaluation
		//create a coin with ai's color
		//terminal = bool that call a method to see if there is a connect 4 or 5 there
		Board copyB = b;
		Coin c = new Coin(color);
		Coin[][] cArray = copyB.getBoard();
		boolean terminal = isTerminal(b);
		//get all possible position where you can insert a coin
		ArrayList<Point> available = getAvailableLoc(copyB);
		
		//if the depth reach 0 or the condition on the board is win or tie,return score
		if(depth == 0 || terminal)
		{
			if(aiWin)
			{
				return 100000000;

			}
			if(playerWin)
			{
				return -100000000;
			}
			else
			{
				return ComputeScore(copyB);
			}
		}
		//for maximizingPlayer
		//try to get the highest score
		if(maximizingPlayer)
		{
			for (int i = 0; i < available.size();i++)
			{
				//set the val to a huge negative number
				//make a move and call minimax to calculate the score
				//if the new score is higher than the current one
				//replace it and add it to the scoreArray in same coordinate
				int val = -100000000;
				Point p = available.get(i);
				int positX = p.getX();
				int positY = p.getY();
				copyB.insert(c, positX, positY);
				val = minimax(copyB, depth - 1, alpha, beta,true);
				if (val > alpha) 
				{ 
                    alpha = val;
                    scoreArray[positX][positY] = alpha;
                }
                if (alpha >= beta) 
                {
                    break;
                }
            }
			return alpha;
		}
		//for minimizingPlayer
		//try to get the lowest score
		else
		{
			for (int i = 0; i < available.size();i++)
			{
				//set the val to a huge positive number
				//make a move and call minimax to calculate the score
				//if the new score is lower than the current one
				//replace it and add it to the scoreArray in same coordinate
				int val = 100000000;
				Point p = available.get(i);
				int positX = p.getX();
				int positY = p.getY();
				copyB.insert(c, positX, positY);
				val = minimax(copyB, depth - 1, alpha, beta,false);
				if (val < beta) 
				{
					beta = val;
					scoreArray[positX][positY] = beta;
					}
				if (alpha >= beta) 
				{
					break;
					}
				}
			return beta;
		}
		
		
}
				
				

			
	//this method calculates score base on different connection of coins in the board
	//for the minimax method to find out the best move(highest point for ai's move or lowest point for player's move)
	private int ComputeScore(Board b) {
		int totalScore = 0;
		//set the color for later use of looking for connected lines
		String oppcolor = null;
		if(color.equals("Red"))
		{
			oppcolor = "Yellow";
		}
		if(color.equals("Yellow"))
		{
			oppcolor = "Red";
		}
		
		Coin[][]c = b.getBoard();
		boolean checkC4 = b.get_connect_four();
		int connectedAi = 0;
		int mostConnectedAi = 0;
		int connectedP = 0;
		int mostConnectedP = 0;
		//search connected lines horizontally
		for(int row = 0; row < c.length;row++)
		{
			for(int col = 0; col < c[row].length;col++)
			{
				if((c[row][col] != null && c[row][col].getColor().equals(color)))
				{
					connectedAi++;
				}
				else
				{
					mostConnectedAi = connectedAi;
					connectedAi = 0;
				}
				if((c[row][col] != null && c[row][col].getColor().equals(oppcolor)))
				{
					connectedP++;
				}
				else
				{
					mostConnectedP = connectedP;
					connectedP = 0;
				}
			}
			totalScore += calculateSystem(mostConnectedAi,mostConnectedP,checkC4);
			mostConnectedAi = 0;
			mostConnectedP = 0;
		}
		//search connected lines vertically
		for(int row = 0; row < c.length;row++)
		{
			for(int col = 0; col < c[row].length;col++)
			{
				if((c[col][row] != null && c[col][row].getColor().equals(color)))
				{
					connectedAi++;
				}
				else
				{
					mostConnectedAi = connectedAi;
					connectedAi = 0;		
				}
				if((c[col][row] != null && c[col][row].getColor().equals(oppcolor)))
				{
					connectedP++;
				}
				else
				{
					mostConnectedP = connectedP;
					connectedP = 0;		
				}
			}
			totalScore += calculateSystem(mostConnectedAi,mostConnectedP,checkC4);
			mostConnectedAi = 0;
			mostConnectedP = 0;
		}
		//search connected lines from diagonal(from upper left to lower right)
		for (int i = c.length - 1; i > 0; i--) 
		{
			for (int j = 0, x = i; x <= c.length - 1; j++, x++) 
			{
				if((c[x][j] != null && c[x][j].getColor().equals(color)))
				{
					connectedAi++;
				}
				else
				{
					mostConnectedAi = connectedAi;
					connectedAi = 0;
				}
				if((c[x][j] != null && c[x][j].getColor().equals(oppcolor)))
				{
					connectedP++;
				}
				else
				{
					mostConnectedP = connectedP;
					connectedP = 0;
				}
			}
			totalScore += calculateSystem(mostConnectedAi,mostConnectedP,checkC4);
			mostConnectedAi = 0;
			mostConnectedP = 0;
					
		}
				
		//search connected lines from diagonal(from lower left to upper right)
		for (int i = 0; i <= c.length - 1; i++) 
		{
			for (int j = 0, y = i; y <= c.length - 1; j++, y++) 
			{
				if((c[j][y] != null && c[j][y].getColor().equals(color)))
				{
					connectedAi++;
				}
				else
				{
					mostConnectedAi = connectedAi;
					connectedAi = 0;
				}
				if((c[j][y] != null && c[j][y].getColor().equals(oppcolor)))
				{
					connectedP++;
				}
				else
				{
					mostConnectedP = connectedP;
					connectedP = 0;
				}
			}
			totalScore += calculateSystem(mostConnectedAi,mostConnectedP,checkC4);
			mostConnectedAi = 0;
			mostConnectedP = 0;
		}
		
		return totalScore;
	}
	//this method is the grading system of the ComputeScore
	//the more coins connected the more points, 
	//if the coins are ai's,give positive points,if the coins are players, give negative points
	//return the total score
	public int calculateSystem(int mostConnectedAi,int mostConnectedP,boolean checkC4)
	{
		int totalScore = 0;
		if(checkC4)
		{
			if(mostConnectedAi == 4)
			{
				totalScore+=1000;
			}
			if(mostConnectedAi == 2)
			{
				totalScore+=20;
			}
			if(mostConnectedAi == 3)
			{
				totalScore+=50;
			}
			if(mostConnectedP == 2)
			{
				totalScore-=10;
			}
			if(mostConnectedP == 3)
			{
				totalScore-=70;
			}
			if(mostConnectedP == 4)
			{
				totalScore-=1000;
			}
		}
		else
		{
			if(mostConnectedAi == 5)
			{
				totalScore+=1000;
			}
			if(mostConnectedAi == 1)
			{
				totalScore+=10;
			}
			if(mostConnectedAi == 2)
			{
				totalScore+=20;
			}
			if(mostConnectedAi == 3)
			{
				totalScore+=50;
			}
			if(mostConnectedAi == 4)
			{
				totalScore+=80;
			}
			if(mostConnectedP == 3)
			{
				totalScore-=60;
			}
			if(mostConnectedP == 4)
			{
				totalScore-=90;
			}
			if(mostConnectedP == 5)
			{
				totalScore-=10000;
			}
		}
		return totalScore;
	}

	//This method is used to find if there is a connect four/connect five done
	public boolean isTerminal(Board b)
	{
		//set the color for later use of looking for connected lines
		String oppcolor = null;
		if(color.equals("Red"))
		{
			oppcolor = "Yellow";
		}
		if(color.equals("Yellow"))
		{
			oppcolor = "Red";
		}
		
		//set the compare value for finding connect four/connect five
		//emptyspace = check if there is any space left to put a coin
		//if not and nobody wins, that is a tie
		int determine;
		int emptyspace = 0;
		if(b.get_connect_four())
		{
			determine = 4;
		}
		else
		{
			determine = 5;
		}
		Coin[][] c = b.getBoard();
		int countAi = 0;
		int countP = 0;
		//search connected lines horizontally
		for(int row = 0; row < c.length;row++)
		{
			for(int col = 0; col < c[row].length;col++)
			{
				//increase the counter of ai coin or player coin for each horizontal lines
				//if two coin next to each other has different color, clear the counter
				//if the counter is not equal to determine number, clear the counter and go to next line
				if((c[row][col] != null && c[row][col].getColor().equals(color)))
				{
					countAi++;
				}
				else
				{
					countAi = 0;
				}
				
				if((c[row][col] != null && c[row][col].getColor().equals(oppcolor)))
				{
					countP++;
				}
				else
				{
					countP = 0;
				}
				if(c[row][col] == null)
				{
					emptyspace++;
				}
			}
			
			//check the counter to see if there is a winner,going to the next loop if not
			if(((countAi == determine)&&(b.get_connect_four())) || ((countAi == determine)&&(b.get_connect_five())))
			{
				aiWin = true;
				return true;
			}
			else if(((countP == determine)&&(b.get_connect_four())) || ((countP == determine)&&(b.get_connect_five())))
			{
				playerWin = true;
				return true;
			}
			else
			{
				countP = 0;
				countAi = 0;
			}
			
		}
		//search connected lines vertically
		for(int row = 0; row < c.length;row++)
		{
			for(int col = 0; col < c[row].length;col++)
			{
				//increase the counter of ai coin or player coin for each vertical lines
				//if two coin next to each other has different color, clear the counter
				//if the counter is not equal to determine number, clear the counter and go to next line
				if((c[col][row] != null && c[col][row].getColor().equals(color)))
				{
					countAi++;
				}
				else
				{
					countAi = 0;
				}
				
				if((c[col][row] != null && c[col][row].getColor().equals(oppcolor)))
				{
					countP++;
				}
				else
				{
					countP = 0;
				}
			}
			//check the counter to see if there is a winner,going to the next loop if not
			if(((countAi == determine)&&(b.get_connect_four())) || ((countAi == determine)&&(b.get_connect_five())))
			{
				aiWin = true;
				return true;
			}
			else if(((countP == determine)&&(b.get_connect_four())) || ((countP == determine)&&(b.get_connect_five())))
			{
				playerWin = true;
				return true;
			}
			else
			{
				countP = 0;
				countAi = 0;
			}
			
		}
		//search connected lines from diagonal(from upper left to lower right)
		for (int i = c.length - 1; i > 0; i--) 
		{
			for (int j = 0, x = i; x <= c.length - 1; j++, x++) 
			{
				if((c[x][j] != null && c[x][j].getColor().equals(oppcolor)))
				{
					countP++;
				}
				else
				{
					countP = 0;
				}
			}
			//check the counter to see if there is a winner,going to the next loop if not
			if(((countAi == determine)&&(b.get_connect_four())) || ((countAi == determine)&&(b.get_connect_five())))
			{
				aiWin = true;
				return true;
			}
			else if(((countP == determine)&&(b.get_connect_four())) || ((countP == determine)&&(b.get_connect_five())))
			{
				playerWin = true;
				return true;
			}
			else
			{
				countP = 0;
				countAi = 0;
			}
		}
		
		//search connected lines from diagonal(from lower left to upper right)
		for (int i = 0; i <= c.length - 1; i++) 
		{
			for (int j = 0, y = i; y <= c.length - 1; j++, y++) 
			{
				if((c[j][y] != null && c[j][y].getColor().equals(oppcolor)))
				{
					countP++;
				}
				else
				{
					countP = 0;
				}
			}
			//check the counter to see if there is a winner,going to the next loop if not
			if(((countAi == determine)&&(b.get_connect_four())) || ((countAi == determine)&&(b.get_connect_five())))
			{
				aiWin = true;
				return true;
			}
			else if(((countP == determine)&&(b.get_connect_four())) || ((countP == determine)&&(b.get_connect_five())))
			{
				playerWin = true;
				return true;
			}
			else
			{
				countP = 0;
				countAi = 0;
			}
		}
		if(emptyspace != 0)
		{
			tie = true;
		}
		return false;
		
	}
}
