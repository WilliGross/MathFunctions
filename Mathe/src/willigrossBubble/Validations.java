package willigrossBubble;


public class Validations {
	
	/**
	 * Validates a function's expression by checking if all symbols are either digits, operators, decimal points or x
	 * @param expression the the string to be validated
	 */
	public static boolean doesExpressionContainValidCharacters(String expression) {
		
		if (expression.matches("[x\\d\\s+-\\\\*/()\\\\^\\\\.]+"))
			return true;
		return false;
	}
	
	/**
	 * Checks if an expression can possibly be converted into a number
	 * @param expression the the string to be validated
	 */
	public static boolean canConvertToNumber(String expression) {
		
		if (expression.matches("[\\d\\s+-\\\\*/()\\\\^\\\\.]+"))
			return true;
		return false;
	}
	
}
