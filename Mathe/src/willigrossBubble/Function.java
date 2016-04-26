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
	 * A string to represent the function
	 */
	public String toString() {
		return expression;
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
		final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
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
		final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
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
	
/*	/**
	 * Find one intersection of two graphs
	 * @param f - function f
	 * @param g - function g
	 * @return the intersection point
	 "star"/
	public static Point findIntersection(Function f, Function g) {
		boolean found = false;
		boolean tryIncreasingX = true;
		int dirChanges = 1;
		double yF = 0, yG = 0, prevYF, prevYG, currX, absDiff = 0, prevAbsDiff;
		Point interS = new Point(0, 0);
		
		currX = Math.floor(Math.random() * 10);
				
		while (!found) {
			prevYF = yF;
			prevYG = yG;
			prevAbsDiff = absDiff;
			
			yF = f.evaluate(currX);
			yG = g.evaluate(currX);
			absDiff = Math.abs(yF - yG);
			
			if (absDiff <= 0.05 && absDiff >= -0.05) { //correct x value found?
				found = true;
				interS.setX(currX);
			} else { // not found yet :(
				
				//change checking direction?
				if (prevAbsDiff <= absDiff) {
					if (tryIncreasingX == true)
						tryIncreasingX = false;
					else
						tryIncreasingX = true;
				}
				
				//set next x value
				if (tryIncreasingX) {
					currX += absDiff / (10 * dirChanges);
				} else {
					currX -= absDiff / (10 * dirChanges);
				}
				
			}
			
		}//end of while loop
		
		//check if integer x value is the exact intersection
		prevYF = yF;
		prevYG = yG;
		prevAbsDiff = absDiff;
		
		currX = Math.round(currX);
		
		yF = f.evaluate(currX);
		yG = g.evaluate(currX);
		absDiff = Math.abs(yF - yG);
		
		if (absDiff == 0) {
			interS.setX(currX);
		}
		
		//calculate corresponding y - value
		interS.setY(f.evaluate(interS.getX()));
		
		return interS;
	}
*/	
}
