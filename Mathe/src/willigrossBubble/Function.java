package willigrossBubble;

import java.util.HashMap;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public abstract class Function {
	
	/**
	 * The actual function
	 */
	private String expression = "";
	
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
	 * This calculates a value table for the expression
	 * @param start - start value for x
	 * @param end - end value for x
	 * @param step - the step between x values
	 * @return the table as a hash map with x as key and corresponding value as value
	 */
	public HashMap<Double, Double> table(double start, double end, double step) {
		
		HashMap<Double, Double> table = new HashMap<Double, Double>();
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		double x = start;
		
		if (start <= end) {
			//increasing x
			while (x <= end) {
				
				variables.set("x", x);
				
				table.put(x, evaluator.evaluate(expression, variables));
				
				x += step;
			}
		} else {
			//decreasing x
			while (x >= end) {
				
				variables.set("x", x);
				
				table.put(x, evaluator.evaluate(expression, variables));
				
				x -= step;
			}
		}
		
		return table;
	}
	
	/**
	 * A String to represent the function
	 */
	public String toString() {
		return expression;
	}
	
}
