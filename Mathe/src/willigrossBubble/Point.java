package willigrossBubble;

public class Point {

	private double x, y;
	
	
	
	
	public double getX(){
		return this.x;
	}
	
	
	public double getY(){
		return this.y;
	}
	
	
	public void setX(double x) {
		this.x = x;
	}


	public void setY(double y) {
		this.y = y;
	}


	public String toString(){
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
	
	
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
}
