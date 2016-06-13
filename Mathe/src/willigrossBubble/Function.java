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
	 * The actual function
	 */
	private String expression = "";
	
	private char name;
	
	
	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}
	
	
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
		
		if (step == 0)
			throw new IllegalArgumentException("Step may not be 0");
		
		ArrayList<String> table = new ArrayList<>();
		
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		
		if (start <= end) {	//increasing x
			
			for (double x = start; x <= end; x += step) {
				variables.set("x", x);
				table.add(name + "(" + x + ") = " + Utility.roundDouble(evaluator.evaluate(expression, variables), 3));
			}
			
		} else {//decreasing x
			
			for (double x = start; x >= end; x -= step) {
				variables.set("x", x);
				table.add(name + "(" + x + ") = " + Utility.roundDouble(evaluator.evaluate(expression, variables), 3));
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
			if (copy.getExpression().startsWith("-(") && copy.getExpression().endsWith(")"))
				copy.setExpression(copy.getExpression().substring(2, copy.getExpression().length() - 1));
			else {
				copy.setExpression("-(" + copy.getExpression() + ")");
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
			if (copy.getExpression().endsWith("x"))
				endsWithX = true;
			
			String[] splitX = copy.getExpression().split("x");
			
			copy.setExpression(splitX[0]);
			
			for (int i = 1; i < splitX.length; i++)
				copy.setExpression(copy.getExpression() + "(-x)" + splitX[i]);
			
			if (endsWithX)
				copy.setExpression(copy.getExpression() + "(-x)");
			
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


	@Override
	public int hashCode() {
		return getExpression().hashCode();
	}
	
	
}
