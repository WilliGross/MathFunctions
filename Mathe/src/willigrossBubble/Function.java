package willigrossBubble;

import javax.swing.JOptionPane;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class Function {
	
	/**
	 * The actual function
	 */
	protected String expression = "";
	
	/**
	 * Directly enter the expression
	 * @param expression - the expression to save as the function
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	

	
	
	/**
	 * This checks whether a point is on the graph
	 * @param p - the point that is checked
	 * @return true, if point is on graph; false, if not
	 */
	public boolean testPointOnGraph(Point p) {
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		variables.set("x", p.getX());
		
		if (evaluator.evaluate(expression, variables) == p.getY()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Displays a value table for the expression
	 * @param start - start value for x
	 * @param end - end value for x
	 * @param step - the step between x values
	 */
	public void table(double start, double end, double step) {
		
		String tableString ="f(x) = " + expression + "\n\n";
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		double x = start;
		
		if (start <= end) {
			//increasing x
			while (x <= end) {
				
				variables.set("x", x);
				
				tableString += "f(" + x + ") = " + roundDouble(evaluator.evaluate(expression, variables), 3) + "\n";
				
				x += step;
			}
		} else {
			//decreasing x
			while (x >= end) {
				
				variables.set("x", x);
				
				tableString += "f(" + x + ") = " + roundDouble(evaluator.evaluate(expression, variables), 3) + "\n";
				
				x -= step;
			}
		}
		
		JOptionPane.showMessageDialog(null, tableString);
		
	}
	
	/**
	 * A string to represent the function
	 */
	public String toString() {
		return expression;
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




	public static Function mirrorX(Function function) {
		
		Function f;
		
		//avoid double -
		if (function.expression.startsWith("-")) {
			
			if (function instanceof ExponentialFunction) {
				f = new ExponentialFunction();
			} else if (function instanceof LinearFunction) {
				f = new LinearFunction();
			} else {
				f = new Function();
			}
			
			f.setExpression(function.expression.substring(1));
			
		//add - otherwise	
		} else {
			
			if (function instanceof ExponentialFunction) {
				f = new ExponentialFunction();
				f.setExpression("-" + function);
				return f;
			
			} else if (function instanceof LinearFunction){
				f = new LinearFunction();
				f.setExpression("-(" + function + ")");
				return f;
			
			} else {
				f = new Function();
				f.setExpression("-(" + function + ")");
				return f;
			}
		}
		
		return f;

	}




	public static Function mirrorY(Function function) {

		String[] splitX = function.expression.split("x");
		Function f = new Function();
		
		
		f.setExpression(splitX[0] + "(-x)");
		if (splitX.length >= 2)
			f.setExpression(f.expression + splitX[1]);

		
		if (function instanceof ExponentialFunction)
			return (ExponentialFunction) f;
		else if (function instanceof LinearFunction)
			return (LinearFunction) f;
		else 
			return f;
	}




	public static Function mirrorOrigin(Function function) {
		
		Function f = mirrorX(function);
		Function g = mirrorY(f);
		
		if (function instanceof ExponentialFunction)
			return (ExponentialFunction) g;
		else if (function instanceof LinearFunction)
			return (LinearFunction) g;
		else 
			return g;
	}
	
}
