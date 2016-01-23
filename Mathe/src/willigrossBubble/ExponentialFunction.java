package willigrossBubble;

public class ExponentialFunction extends Function {


	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 */
	public void createThroughPoints(Point p, Point q) {
		double a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		double b = p.getY() / Math.pow(a, p.getX());
		
		 expression = Function.roundDouble(b, 3) + " * " + Function.roundDouble(a, 3) + "^x";
	}

	
	
}
