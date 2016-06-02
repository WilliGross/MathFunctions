package willigrossBubble;

public class ExponentialFunction extends Function {

	private static final long serialVersionUID = 1L;

	private ExponentialFunction(String expression) {
		super(expression);
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
				throw new InvalidPointConfigurationException("Invalid point configuration: same y coordinates"); //"Invalid point configuration: Points have the same y coordinate, exponential functions can never be perfectly horizontal!"
			if (Utility.arePointsOnVerticalLine(p, q))
				throw new InvalidPointConfigurationException("Invalid point configuration: same x coordinates"); //"Invalid point configuration: Points have the same x coordinate, exponential functions can never be perfectly vertical!"
		} //TODO filter points in wrong quadrants
		
		String expression = "";
		
		double a, b;
		
		if (p.equals(q)) {
			a = Math.pow(p.getY(), 1 / p.getX());
			
			if (a - (int) a == 0)
				expression += (int) a + "^x";
			else
				expression += Function.roundDouble(a, 3) + "^x";
			
			return new ExponentialFunction(expression);
		}
		
		
		a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		b = p.getY() / Math.pow(a, p.getX());
		

		if ( a == 0 || b == 0)
			expression = "0";
		else {

			if (b - (int) b == 0)
				expression += (b != 1.0) ? (int) b + " * " : ""; 
			else
				expression += (b != 1.0) ? Function.roundDouble(b, 3) + " * " : "";

				
			if (a - (int) a == 0)
				expression += (int) a + "^x";
			else
				expression += Function.roundDouble(a, 3) + "^x";
			
			
		}
		return new ExponentialFunction(expression);
	}

}
