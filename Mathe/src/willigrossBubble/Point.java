package willigrossBubble;

import java.io.Serializable;

public class Point implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	@Override
	public String toString(){
		return "(" + Function.roundDouble(this.getX(), 3) + "," + Function.roundDouble(this.getY(), 3) + ")";
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




	/**
	 * Overwriting of .equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (x == ((Point) obj).getX() && y == ((Point) obj).getY())
			return true;
		return false;
	}




	/**
	 * Overwriting of .hashCode()
	 */
	@Override
	public int hashCode() {
		return (new Double(x + y)).hashCode();
	}
	
	
	
	
	
}
