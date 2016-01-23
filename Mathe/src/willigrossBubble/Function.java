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
				
				tableString += "f(" + x + ") = "  +evaluator.evaluate(expression, variables) + "\n";
				
				x += step;
			}
		} else {
			//decreasing x
			while (x >= end) {
				
				variables.set("x", x);
				
				tableString += "f(" + x + ") = "  +evaluator.evaluate(expression, variables) + "\n";
				
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
	
}
