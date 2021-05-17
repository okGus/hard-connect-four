//This class is used to represent the address in a 2d array
//it contains two int values:x and y
public class Point {

	private int x;
	private int y;
	
	public Point()
	{
		x = 0;
		y = 0;
	}
	
	public Point(int x,int y)
	{
		this.x = x;
		this.y = y;
	}

	//getter for x and y
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
