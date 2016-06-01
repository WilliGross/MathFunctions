package willigrossBubble;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Utility {
	
	/**
	 * A utility method that converts a string expression into a double value
	 * @param input the string input
	 * @return the calculated double value
	 * @throws IllegalArgumentException if input can't be converted to a number
	 */
	public static double readDoubleFromStringInput(String input) throws IllegalArgumentException {

		if (!Validations.canConvertToNumber(input))
			throw new IllegalArgumentException("Can't convert input into a number!");
		
		DoubleEvaluator evaluator = new DoubleEvaluator();

		return evaluator.evaluate(input);
	}
	
	/**
	 * Checks if two points lie on a horizontal line
	 * @param p point 1
	 * @param q point 2
	 * @return true if they do
	 */
	public static boolean arePointsOnHorzontalLine(Point p, Point q) {
		if (p.getY() == q.getY())
			return true;
		return false;
	}
	
	/**
	 * Checks if two points lie on a vertical line
	 * @param p point 1
	 * @param q point 2
	 * @return true if they do
	 */
	public static boolean arePointsOnVerticalLine(Point p, Point q) {
		if (p.getX() == q.getX())
			return true;
		return false;
	}
}
