package willigrossBubble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import willigrossBubble.gui.FrameMain;

public class Function implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * not rounded expression for internal use
	 */
	private String expression = ""; //$NON-NLS-1$
	
	/**
	 * rounded expression for display 
	 */
	private String expressionRounded = ""; //$NON-NLS-1$
	
	private char name;
	
	
	public Function(String expression) {
		this(expression, expression);
	}
	
	public Function(String expression, String expressionRounded) {
		this.expression = expression.replaceAll("\\s", ""); //$NON-NLS-1$ //$NON-NLS-2$
		this.expressionRounded = expressionRounded;
		name = FrameMain.getInstance().getMainLogic().getNextName();
	}


	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}
	
	
	/**
	 * @return the expressionRounded
	 */
	public String getExpressionRounded() {
		return expressionRounded;
	}


	/**
	 * @return the name
	 */
	public char getName() {
		return name;
	}
	
	
	/**
	 * Directly enter the expression (updates rounded expression as well)
	 * @param expression - the expression to save as the function
	 */
	public void setExpression(String expression) {
		this.expression = expression;
		expressionRounded = expression;
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
		return name + "(x) = " + expressionRounded; //$NON-NLS-1$
	}
	
	
	/**
	 * Evaluates the expression with a specified x value
	 * @param x - the x value 
	 * @return the corresponding y value
	 */
	public double evaluate(double x) {
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		variables.set("x", x); //$NON-NLS-1$
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
		
		variables.set("x", p.getX()); //$NON-NLS-1$
		
		value = evaluator.evaluate(expression, variables);
		if (value > p.getY() - 0.00001 && value < p.getY() + 0.00001) { //tolerance of 0.00001
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
		
		if (step == 0)
			throw new IllegalArgumentException(Strings.getStringAsHTML("Function.IAE_stepZero")); //$NON-NLS-1$
		
		ArrayList<String> table = new ArrayList<>();
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		
		if (start <= end) {	//increasing x
			
			for (double x = start; x <= end; x += step) {
				variables.set("x", x); //$NON-NLS-1$
				table.add(name + "(" + x + ") = " + Utility.roundDouble(evaluator.evaluate(expression, variables), 3)); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
		} else {//decreasing x
			
			for (double x = start; x >= end; x -= step) {
				variables.set("x", x); //$NON-NLS-1$
				table.add(name + "(" + x + ") = " + Utility.roundDouble(evaluator.evaluate(expression, variables), 3)); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
		}
		
		return table.toArray(new String[table.size()]);
	}
	
	
	/**
	 * Mirror a function on the x-axis
	 * @return the new function, null if something went wrong in deepCopy()
	 */
	public Function mirrorX() {
		try {
			Function copy = deepCopy();
			if (copy.getExpression().startsWith("-(") && copy.getExpression().endsWith(")")) //$NON-NLS-1$ //$NON-NLS-2$
				copy.setExpression(copy.getExpression().substring(2, copy.getExpression().length() - 1));
			else {
				copy.setExpression("-(" + copy.getExpression() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
				copy.setName(FrameMain.getInstance().getMainLogic().getNextName());
			}
			return copy;
		} catch (IOException e) {
			System.err.println(e);
		}
		return null;
	}
	
	
	/**
	 * Mirror a function on the y-axis
	 * @return the new function, null if something went wrong in deepCopy()
	 */
	public Function mirrorY() {
		try {
			Function copy = deepCopy();
			boolean endsWithX = false;
			if (copy.getExpression().endsWith("x")) //$NON-NLS-1$
				endsWithX = true;
			
			String[] splitX = copy.getExpression().split("x"); //$NON-NLS-1$
			
			copy.setExpression(splitX[0]);
			
			for (int i = 1; i < splitX.length; i++)
				copy.setExpression(copy.getExpression() + "(-x)" + splitX[i]); //$NON-NLS-1$
			
			if (endsWithX)
				copy.setExpression(copy.getExpression() + "(-x)"); //$NON-NLS-1$
			
			copy.setName(FrameMain.getInstance().getMainLogic().getNextName());
			return copy;
		} catch (IOException e) {
			System.err.println(e);
		}
		return null;
	}
		
	
	/**
	 * Mirror a function on the x-axis and y-axis => rotate it around the origin
	 * @return the new function, null if something went wrong in deepCopy()
	 */
	public Function mirrorOrigin() {
		
		return mirrorX().mirrorY();
	}
	
	
	/**
	 * Creates a deep copy of the function using serialization and deserialization 
	 * @return the copy of the function, null if something went wrong
	 * @throws IOException
	 */
	public Function deepCopy() throws IOException {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(baos);
			out.writeObject(this);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			in = new ObjectInputStream(bais);
			Object copy = in.readObject();
			
			if (this instanceof ExponentialFunction)
				return (ExponentialFunction) copy;
			if (this instanceof LinearFunction)
				return (LinearFunction) copy;
			return (Function) copy;
		} catch (ClassNotFoundException | IOException e) {
			System.err.println(e);
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
		return null;
	}


	/**
 	 * override .equals so that two functions that have the same expression are equal 
 	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (obj == this) 
			return true;
		if (! (obj instanceof Function)) 
			return false;
		if (((Function) obj).getExpression().equals(getExpression()))
			return true;
		return false;
	}

	
	/**
	 * override. hashcode by generating the hash with the expression of the function 
	 */
	@Override
	public int hashCode() {
		return getExpression().hashCode();
	}
	
	
}
