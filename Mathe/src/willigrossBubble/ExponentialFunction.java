package willigrossBubble;

public class ExponentialFunction extends Function {

	private static final long serialVersionUID = 1L;

	/**Implementation of super constructor*/
	private ExponentialFunction(String expression) {
		super(expression);
	}
	
	/**Implementation of super constructor*/
	private ExponentialFunction(String expression, String expressionRounded) {
		super(expression, expressionRounded);
	}

	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 * @throws InvalidPointConfigurationException 
	 */
	public static ExponentialFunction createThroughPoints(Point p, Point q) throws InvalidPointConfigurationException {
		
//		if (p.equals(q))
//			throw new InvalidPointConfigurationException("Invalid point configuration: Points are equal to each other, can't create a unique function!");
		
		if (!p.equals(q)) {
			if (Utility.arePointsOnHorzontalLine(p, q))
				throw new InvalidPointConfigurationException("Invalid point configuration: same y coordinates", "Exponential functions can never be perfectly horizontal!"); //"Invalid point configuration: Points have the same y coordinate, exponential functions can never be perfectly horizontal!"
			if (Utility.arePointsOnVerticalLine(p, q))
				throw new InvalidPointConfigurationException("Invalid point configuration: same x coordinates", "Exponential functions can never be perfectly vertical!"); //"Invalid point configuration: Points have the same x coordinate, exponential functions can never be perfectly vertical!"
		}
		
		if ( 	((p.getQuadrant() == 1 || p.getQuadrant() == 2) && (q.getQuadrant() == 3 || q.getQuadrant() == 4)) || 	// p above x and q below
				((q.getQuadrant() == 1 || q.getQuadrant() == 2) && (p.getQuadrant() == 3 || p.getQuadrant() == 4)) )	// q above x and p below
			throw new InvalidPointConfigurationException("Invalid point configuration: points in invalid quadrants", "Exponential functions can't have positive AND negative values!");
		
		if (p.getQuadrant() == 6 || q.getQuadrant() == 6)
			throw new InvalidPointConfigurationException("Invalid point configuration: points can't be on x-axis", "exponential functions never reach the x-axis");
		
		String expression = "";
		String expressionRounded = "";
		
		double a, b;
		
		if (p.equals(q)) {
			a = Math.pow(p.getY(), 1 / p.getX());
			
			if (a - (int) a == 0) {
				expression += (int) a + "^x";
				expressionRounded += (int) a + "^x";
			} else {
				expression += a + "^x";
				expression += Utility.roundDouble(a, 3) + "^x";
			}
			
			return new ExponentialFunction(expression, expressionRounded);
		}
		
		
		a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		b = p.getY() / Math.pow(a, p.getX());
		

		if ( a == 0 || b == 0) {
			expression = "0";
			expressionRounded = "0";
		} else {

			if (b - (int) b == 0) {
				expression += (b != 1.0) ? (int) b + " * " : ""; 
				expressionRounded += (b != 1.0) ? (int) b + " * " : ""; 
			} else {
				expression += (b != 1.0) ? b + " * " : "";
				expressionRounded += (b != 1.0) ? Utility.roundDouble(b, 3) + " * " : "";
			}
				
			if (a - (int) a == 0) {
				expression += (int) a + "^x";
				expressionRounded += (int) a + "^x";
			} else {
				expression += a + "^x";
				expressionRounded += Utility.roundDouble(a, 3) + "^x";
			}
			
		}
		return new ExponentialFunction(expression, expressionRounded);
	}

}
