package willigrossBubble;

public class Point {

	/**
	 * The x and y coordinate of the point
	 */
	private double x, y;
	

	

	/**Get the point's x coordinate
	 * @return the point's x coordinate
	 */
	public double getX() {
		return x;
	}




	/**Get the point's x coordinate
	 * @return the point's y coordinate
	 */
	public double getY() {
		return y;
	}




	/**Set the point's x coordinate
	 * @param x - the x coordinate to set
	 */
	public void setX(double x) {
		this.x = x;
	}




	/**Set the point's y coordinate
	 * @param y - the y coordinate to set
	 */
	public void setY(double y) {
		this.y = y;
	}




	/**
	 * A string to represent the point
	 */
	public String toString(){
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
	
	
	/**
	 * A point's constructor
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
}
