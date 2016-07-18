package willigrossBubble;

public class LinearFunction extends Function {
	
	private static final long serialVersionUID = 1L;
	
	/**Implementation of super constructor*/
	private LinearFunction(String expression) {
		super(expression);
	}
	
	/**Implementation of super constructor*/
	private LinearFunction(String expression, String expressionRounded) {
		super(expression, expressionRounded);
	}
	
	
	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 * @throws InvalidPointConfigurationException if px = qx but points are not identical because there is no function f(x) for a vertical straight line
	 */
	public static LinearFunction createThroughPoints(Point p, Point q) throws InvalidPointConfigurationException {
		
		String expression = ""; //$NON-NLS-1$
		String expressionRounded = ""; //$NON-NLS-1$
		
		if (p.getX() == q.getX() && !p.equals(q)) {
			throw new InvalidPointConfigurationException(Strings.getString("LinearFunction.IPCE_sameXValues_message"), Strings.getString("LinearFunction.IPCE_sameXValues_tooltip")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		
		double m = (q.getY() - p.getY()) / (q.getX() - p.getX());
		
		if (Double.isNaN(m))
			m = 0;
		
		double t = p.getY() - m * p.getX();
		
		
		if ( m != 0) {
			if (m - (int) m == 0) {
				expression += (m != 1.0) ? (int) m + " * x" : "x";  //$NON-NLS-1$ //$NON-NLS-2$
				expressionRounded += (m != 1.0) ? (int) m + " * x" : "x";  //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				expression += (m != 1.0) ? m + " * x" : "x"; //$NON-NLS-1$ //$NON-NLS-2$
				expressionRounded += (m != 1.0) ? Utility.roundDouble(m, 3) + " * x" : "x"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		
		if (!expression.equals("") && t != 0) { //$NON-NLS-1$
			expression += " + "; //$NON-NLS-1$
			expressionRounded += " + "; //$NON-NLS-1$
		}
		if (t - (int) t == 0) {
			expression += (t != 0.0) ? (int) t : ""; //$NON-NLS-1$
			expressionRounded += (t != 0.0) ? (int) t : ""; //$NON-NLS-1$
		} else {
			expression += (t != 0.0) ? t : ""; //$NON-NLS-1$
			expressionRounded += (t != 0.0) ? Utility.roundDouble(t, 3) : ""; //$NON-NLS-1$
		}
		
		return new LinearFunction(expression, expressionRounded);
	}
	
	
}
