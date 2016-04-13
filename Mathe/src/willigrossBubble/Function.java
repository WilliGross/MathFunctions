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
		double value;
		
		variables.set("x", p.getX());
		
		value = evaluator.evaluate(expression, variables);
		if (value > p.getY() - 0.05 && value < p.getY() + 0.05) {
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
		
		StringBuffer sb = new StringBuffer("f(x) = " + expression + "\n\n");
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		double x = start;
		
		if (start <= end) {
			//increasing x
			while (x <= end) {
				
				variables.set("x", x);
				
				sb.append("f(" + x + ") = " + roundDouble(evaluator.evaluate(expression, variables), 3) + "\n");
				
				x += step;
			}
		} else {
			//decreasing x
			while (x >= end) {
				
				variables.set("x", x);
				
				sb.append("f(" + x + ") = " + roundDouble(evaluator.evaluate(expression, variables), 3) + "\n");
				
				x -= step;
			}
		}
		
		JOptionPane.showMessageDialog(null, sb.toString());
		
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



	/**
	 * Mirror a function on the x-axis
	 * @return the new function
	 */
	public Function mirrorX() {
			this.setExpression("-(" + this + ")");
		return this;
	}



	/**
	 * Mirror a function on the y-axis
	 * @return the new function
	 */
	public Function mirrorY() {

		boolean endsWithX = false;
		if (this.expression.endsWith("x"))
			endsWithX = true;
			
		String[] splitX = expression.split("x");
		
		this.setExpression(splitX[0]);
		
		for (int i = 1; i < splitX.length; i++)
			this.setExpression(this.expression + "(-x)" + splitX[i]);
		
		if (endsWithX)
			this.setExpression(this.expression + "(-x)");

		return this;
	}



	/**
	 * Mirror a function on the x-axis and y-axis => rotate it around the origin
	 * @return the new function
	 */
	public Function mirrorOrigin() {
		
		return this.mirrorX().mirrorY();
	}
	
}
