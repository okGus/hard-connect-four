import java.lang.Math;
//This is a fool AI that inserts coin at random position
//@Param = Board for changing data inside

public class FoolAI extends Player implements playerInterface{

	public FoolAI(String color)
	{
		super(color);
	}
	
	@Override
	public Board act(Board b) 
	{
		//check what board the game is playing
		boolean checkC4 = b.get_connect_four();
		boolean checkC5 = b.get_connect_five();
		//create a new coin which represent AI coin
		Coin c = new Coin(color);
				
		//int for taking value of the position where the AI is going to put the coin at
		//default at [0,0], but will change later
		int row = 0; 
		int col = 0;
		//generate random number for both row and column
		//6*7 board for connect-four
		if(checkC4)
		{
			row = (int)(Math.random() * 8-1+1) + 1;
			col = (int)(Math.random() * 8-1+1) + 1;
		}
		//6*9 board for connect-five
		if(checkC5)
		{
			row = (int)(Math.random() * 8-1+1) + 1;
			col = (int)(Math.random() * 8-1+1) + 1;
		}
		//insert the coin to the board and return it
		b.insert(c,row,col);
		return b;	
	}
}
