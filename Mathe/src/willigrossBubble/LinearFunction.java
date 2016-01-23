package willigrossBubble;

public class LinearFunction extends Function {

	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 */
	public void createThroughPoints(Point p, Point q) {
		double m = (q.getY() - p.getY()) / (q.getX() - p.getX());
		double t = p.getY() - m * p.getX();
		
		 expression = Function.roundDouble(m, 3) + " * x + " + Function.roundDouble(t, 3);
	}
	
	
}
