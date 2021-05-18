//This class is used to represent the address in a 2d array
//it contains two int values:x and y
public class Point {

	private int x;
	private int y;
	
	/**
	 * Inializes Point object to default 0, 0
	 */
	public Point()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * Initializes Point object given x and y respectively
	 * @param x - x
	 * @param y - y
	 */
	public Point(int x,int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns x coordinate point
	 * @return - x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns y coordinate point
	 * @return - y
	 */
	public int getY() {
		return y;
	}
	
	
}
