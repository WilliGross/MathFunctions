package willigrossBubble.core.logic;

import willigrossBubble.data.Strings;

public class ExponentialFunction extends Function {

	private static final long serialVersionUID = 1L;

	/** Implementation of super constructor */
	private ExponentialFunction(String expression) {
		super(expression);
	}
	
	/** Implementation of super constructor */
	private ExponentialFunction(String expression, String expressionRounded) {
		super(expression, expressionRounded);
	}

	/**
	 * Create a function whose graph runs through two given points
	 *
	 * @param p
	 *            - point 1
	 * @param q
	 *            - point 2
	 * @throws InvalidPointConfigurationException
	 */
	public static ExponentialFunction createThroughPoints(Point p, Point q) throws InvalidPointConfigurationException {

		//		if (p.equals(q))
		//		throw new InvalidPointConfigurationException("Invalid point configuration: Points are equal to each other, can't create a unique function!");
		
		if (!p.equals(q)) {
			if (UtilityLogic.arePointsOnHorzontalLine(p, q))
				throw new InvalidPointConfigurationException(
						Strings.getStringAsHTML("ExponentialFunction.IPCE_sameYValues_message"), //$NON-NLS-1$
						Strings.getStringAsHTML("ExponentialFunction.IPCE_sameYValues_tooltip")); //"Invalid point configuration: Points have the same y coordinate, exponential functions can never be perfectly horizontal!"  //$NON-NLS-1$
			if (UtilityLogic.arePointsOnVerticalLine(p, q))
				throw new InvalidPointConfigurationException(
						Strings.getStringAsHTML("ExponentialFunction.IPCE_sameXValues_message"), //$NON-NLS-1$
						Strings.getStringAsHTML("ExponentialFunction.IPCE_sameXValues_tooltip")); //"Invalid point configuration: Points have the same x coordinate, exponential functions can never be perfectly vertical!"  //$NON-NLS-1$
		}
		
		if ((((p.getQuadrant() == 1) || (p.getQuadrant() == 2)) && ((q.getQuadrant() == 3) || (q.getQuadrant() == 4))) // p above x and q below
				|| (((q.getQuadrant() == 1) || (q.getQuadrant() == 2))
						&& ((p.getQuadrant() == 3) || (p.getQuadrant() == 4)))) // q above x and p below
			throw new InvalidPointConfigurationException(
					Strings.getStringAsHTML("ExponentialFunction.IPCE_invalidQuadrants_message"), //$NON-NLS-1$
					Strings.getStringAsHTML("ExponentialFunction.IPCE_invalidQuadrants_tooltip")); //$NON-NLS-1$

		if ((p.getQuadrant() == 6) || (q.getQuadrant() == 6))
			throw new InvalidPointConfigurationException(
					Strings.getStringAsHTML("ExponentialFunction.IPCE_x-axis_message"), //$NON-NLS-1$
					Strings.getStringAsHTML("ExponentialFunction.IPCE_x-axis_tooltip")); //$NON-NLS-1$

		String expression = ""; //$NON-NLS-1$
		String expressionRounded = ""; //$NON-NLS-1$
		
		double a, b;
		
		if (p.equals(q)) {
			a = Math.pow(p.getY(), 1 / p.getX());
			
			if ((a - (int) a) == 0) {
				expression += (int) a + "^x"; //$NON-NLS-1$
				expressionRounded += (int) a + "^x"; //$NON-NLS-1$
			} else {
				expression += a + "^x"; //$NON-NLS-1$
				expression += UtilityLogic.roundDouble(a, 3) + "^x"; //$NON-NLS-1$
			}
			
			return new ExponentialFunction(expression, expressionRounded);
		}

		a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		b = p.getY() / Math.pow(a, p.getX());

		if ((a == 0) || (b == 0)) {
			expression = "0"; //$NON-NLS-1$
			expressionRounded = "0"; //$NON-NLS-1$
		} else {

			if ((b - (int) b) == 0) {
				expression += (b != 1.0) ? (int) b + " * " : ""; //$NON-NLS-1$ //$NON-NLS-2$
				expressionRounded += (b != 1.0) ? (int) b + " * " : ""; //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				expression += (b != 1.0) ? b + " * " : ""; //$NON-NLS-1$ //$NON-NLS-2$
				expressionRounded += (b != 1.0) ? UtilityLogic.roundDouble(b, 3) + " * " : ""; //$NON-NLS-1$ //$NON-NLS-2$
			}

			if ((a - (int) a) == 0) {
				expression += (int) a + "^x"; //$NON-NLS-1$
				expressionRounded += (int) a + "^x"; //$NON-NLS-1$
			} else {
				expression += a + "^x"; //$NON-NLS-1$
				expressionRounded += UtilityLogic.roundDouble(a, 3) + "^x"; //$NON-NLS-1$
			}
			
		}
		return new ExponentialFunction(expression, expressionRounded);
	}

}
