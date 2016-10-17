package willigrossBubble.core.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigrossBubble.data.Strings;

public class LinearFunction extends Function {

	private static final long	serialVersionUID	= 1L;
	
	private static final Logger	logger				= LoggerFactory.getLogger(LinearFunction.class);

	/** Implementation of super constructor */
	private LinearFunction(String expression) {
		super(expression);
	}

	/** Implementation of super constructor */
	private LinearFunction(String expression, String expressionRounded) {
		super(expression, expressionRounded);
	}
	
	/**
	 * Create a function whose graph runs through two given points
	 *
	 * @param p - point 1
	 * @param q - point 2
	 * @throws InvalidPointConfigurationException if px = qx but points are not identical because there is no function
	 *             f(x) for a vertical straight line
	 */
	public static LinearFunction createThroughPoints(Point p, Point q) throws InvalidPointConfigurationException {

		logger.info("Starting calculation of linear function through points P{} and Q{} ...", p, q); //$NON-NLS-1$

		String expression = ""; //$NON-NLS-1$
		String expressionRounded = ""; //$NON-NLS-1$

		if ((p.getX() == q.getX()) && !p.equals(q)) {
			final InvalidPointConfigurationException e = new InvalidPointConfigurationException(
					Strings.getStringAsHTML("LinearFunction.IPCE_sameXValues_message"), //$NON-NLS-1$
					Strings.getStringAsHTML("LinearFunction.IPCE_sameXValues_tooltip")); //$NON-NLS-1$
			logger.warn("Invalid point configuration: Points have the same x coordinate"); //$NON-NLS-1$
			logger.warn("Can't calculate function!"); //$NON-NLS-1$
			throw e;
		}
		
		double m = (q.getY() - p.getY()) / (q.getX() - p.getX());

		if (Double.isNaN(m))
			m = 0;
		
		logger.info("Calculated slope m: {}", m); //$NON-NLS-1$

		final double t = p.getY() - (m * p.getX());
		
		logger.info("Calculated intercept t: {}", t); //$NON-NLS-1$
		
		if (m != 0)
			if ((m - (int) m) == 0) {
				expression += (m != 1.0) ? (int) m + " * x" : "x"; //$NON-NLS-1$ //$NON-NLS-2$
				expressionRounded += (m != 1.0) ? (int) m + " * x" : "x"; //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				expression += (m != 1.0) ? m + " * x" : "x"; //$NON-NLS-1$ //$NON-NLS-2$
				expressionRounded += (m != 1.0) ? UtilityLogic.roundDouble(m, 3) + " * x" : "x"; //$NON-NLS-1$ //$NON-NLS-2$
			}

		if (!expression.equals("") && (t != 0)) { //$NON-NLS-1$
			expression += " + "; //$NON-NLS-1$
			expressionRounded += " + "; //$NON-NLS-1$
		}
		if ((t - (int) t) == 0) {
			expression += (t != 0.0) ? (int) t : ""; //$NON-NLS-1$
			expressionRounded += (t != 0.0) ? (int) t : ""; //$NON-NLS-1$
		} else {
			expression += (t != 0.0) ? t : ""; //$NON-NLS-1$
			expressionRounded += (t != 0.0) ? UtilityLogic.roundDouble(t, 3) : ""; //$NON-NLS-1$
		}

		return new LinearFunction(expression, expressionRounded);
	}
	
}
