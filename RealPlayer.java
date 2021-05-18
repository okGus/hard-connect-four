/**
 * This class is a subclass of the Player class, simulate the real player
 *
 */

public class RealPlayer extends Player implements playerInterface 
{
	//@param = String passed from ConnectFourMain.java 
    public RealPlayer(String color) {
        super(color);
    }

    @Override
    //@param = board passed from game, integer of the coordinates
    public Board act(Board b,int r, int c)
	{
		
		Coin coin = new Coin(super.getColor());
		
		int row = r;
		int col = c;
		
		b.insert(coin,row,col);
		return b;
		
	}
}