package willigrossBubble;

import java.io.Serializable;
import java.util.ArrayList;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import willigrossBubble.gui.FrameMain;

public class Function implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The actual function
	 */
	private String expression = "";
	
	
	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}


	public char name;

	public static String s;
	
	public Function(String expression) {
		this.expression = expression;
		name = FrameMain.getInstance().getMainLogic().getNextName();
	}
	
	
	/**
	 * @return the name
	 */
	public char getName() {
		return name;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(char name) {
		this.name = name;
	}

	/**
	 * A string to represent the function
	 */
	@Override
	public String toString() {
		return name + "(x) = " + expression;
	}


	/**
	 * Directly enter the expression
	 * @param expression - the expression to save as the function
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	/**
	 * Evaluates the expression with a specified x value
	 * @param x - the x value 
	 * @return the corresponding y value
	 */
	public double evaluate(double x) {
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		variables.set("x", x);
		return evaluator.evaluate(expression, variables);
	}
	
	
	/**
	 * This checks whether a point is on the graph
	 * @param p - the point that is checked
	 * @return true, if point is on graph; false, if not
	 */
	public boolean testPointOnGraph(Point p) {
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		double value;
		
		variables.set("x", p.getX());
		
		value = evaluator.evaluate(expression, variables);
		if (value > p.getY() - 0.05 && value < p.getY() + 0.05) { //tolerance of 0.05
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
	public String[] table(double start, double end, double step) {

		ArrayList<String> table = new ArrayList<>();
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		
		if (start <= end) {	//increasing x

			for (double x = start; x <= end; x += step) {
				variables.set("x", x);
				table.add(name + "(" + x + ") = " + roundDouble(evaluator.evaluate(expression, variables), 3));
			}
			
		} else {//decreasing x
			
			for (double x = start; x >= end; x -= step) {
				variables.set("x", x);
				table.add(name + "(" + x + ") = " + roundDouble(evaluator.evaluate(expression, variables), 3));
			}
			
		}
		
		return table.toArray(new String[table.size()]);
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
