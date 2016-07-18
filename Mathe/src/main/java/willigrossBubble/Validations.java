package willigrossBubble;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class Validations {
	
	/**
	 * Validates a function's expression by checking if all symbols are either digits, operators, decimal points or x
	 * @param expression the the string to be validated
	 */
	public static boolean doesExpressionContainValidCharacters(String expression) {
		
		DoubleEvaluator evaluator = new DoubleEvaluator();
		
		try {
			final StaticVariableSet<Double> variables = new StaticVariableSet<>();
			variables.set("x", (double) 1); //$NON-NLS-1$
			evaluator.evaluate(expression, variables);
			return true;
		} catch (@SuppressWarnings("unused") IllegalArgumentException e) {
			return false;
		}
		
//		if (expression.matches("[x\\d\\s+-\\\\*/()\\\\^\\\\.]+"))
	}
	
	/**
	 * Checks if an expression can possibly be converted into a number
	 * @param expression the the string to be validated
	 */
	public static boolean canConvertToNumber(String expression) {
		
		DoubleEvaluator evaluator = new DoubleEvaluator();
		
		try {
			evaluator.evaluate(expression);
			return true;
		} catch (@SuppressWarnings("unused") IllegalArgumentException e) {
			return false;
		}
		
//		if (expression.matches("[\\d\\s+-\\\\*/()\\\\^\\\\.]+"))
	}
	
}
