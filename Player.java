import java.util.Scanner;

//this class is for player movement and the base of AI.
//may not be used if shifting to clicking since this is using keyboard
//@Param = Board for changing data inside
public abstract class Player {

	public Board act(Board b)
	{
		//create scanner for entering input from user
		Scanner scan = new Scanner(System.in);
		//create a new coin which represent player coin
		Coin c = new Coin();
		//int for taking input of the position where the player want to insert the coin
		int row, col;
		//ask for user input and set the value to row and col
		String rowMessage = "Enter the row you want to insert: ";
		System.out.println(rowMessage);
		row = scan.nextInt();
		String colMessage = "Enter the column you want to insert: ";
		System.out.println(colMessage);
		col = scan.nextInt();
		//insert the coin to the board and return it
		b.insert(c,row,col);
		scan.close();
		return b;
		
	}
}
