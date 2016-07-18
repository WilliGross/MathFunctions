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

		DoubleEvaluator evaluator = new DoubleEvaluator();
		
		evaluator.evaluate(input);
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
	
	/**
	 * Round a double value to a specified number of decimals
	 * @param doubleValue - the object to be rounded
	 * @param decimals - the number of decimals
	 * @return the rounded double
	 */
	public static double roundDouble(double doubleValue, int decimals) {
		return Math.round(Math.pow(10, decimals) * doubleValue) / Math.pow(10, decimals);
	}
}
