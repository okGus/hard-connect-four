//this class is for player movement and the base of AI.
//value taking from clicking on javafx screen
//@Param = Board for changing data inside
public abstract class Player{

	protected String color;
	
	public Player(String color)
	{
		this.color = color;
	}

	public Board act(Board b,int r, int c)
	{
		
		Coin coin = new Coin(color);
		
		int row = r;
		int col = c;
		
		b.insert(coin,row,col);
		return b;
		
	}

	public void setColor(String color) {
		this.color = color;
	}
}
