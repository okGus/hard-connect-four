public class RealPlayer extends Player implements playerInterface {
    public RealPlayer(String color) {
        super(color);
    }

    @Override
    public Board act(Board b,int r, int c)
	{
		
		Coin coin = new Coin(super.getColor());
		
		int row = r;
		int col = c;
		
		b.insert(coin,row,col);
		return b;
		
	}

	@Override
	public Board act(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
}