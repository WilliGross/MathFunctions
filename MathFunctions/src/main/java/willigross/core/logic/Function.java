package willigross.core.logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import willigross.core.Controller;
import willigross.data.Strings;

public class Function implements Serializable {

	private static final long	serialVersionUID	= 4513726210175983231L;

	private static final Logger	logger				= LoggerFactory.getLogger(Function.class);
	
	/**
	 * not rounded expression for internal use
	 */
	private String				expression			= "";										//$NON-NLS-1$

	/**
	 * rounded expression for display
	 */
	private String				expressionRounded	= "";										//$NON-NLS-1$

	private char				name;

	public Function(String expression) {
		this(expression, expression);
	}

	public Function(String expression, String expressionRounded) {
		this.expression = expression.replaceAll("\\s", ""); //$NON-NLS-1$ //$NON-NLS-2$
		this.expressionRounded = expressionRounded;
		name = Controller.getInstance().getLogicController().getNextName();
		logger.info("Creating function with expression {} and rounded expression {}", expression, expressionRounded); //$NON-NLS-1$
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
	 * @param expression - the expression to save as the function
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * @param expressionRounded the expressionRounded to set
	 */
	public void setExpressionRounded(String expressionRounded) {
		this.expressionRounded = expressionRounded;
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
	 *
	 * @param x - the x value
	 * @return the corresponding y value
	 */
	public double evaluate(double x) {
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		variables.set("x", x); //$NON-NLS-1$
		final double result = evaluator.evaluate(expression, variables);
		logger.info("Evaluating {} for x = {}: {}", toString(), x, result); //$NON-NLS-1$
		return result;
	}

	/**
	 * This checks whether a point is on the graph
	 *
	 * @param p - the point that is checked
	 * @return true, if point is on graph; false, if not
	 */
	public boolean testPointOnGraph(Point p) {
		boolean result = false;
		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();
		double value;

		variables.set("x", p.getX()); //$NON-NLS-1$

		value = evaluator.evaluate(expression, variables);
		if ((value > (p.getY() - 0.00001)) && (value < (p.getY() + 0.00001)))
			result = true;
		logger.info("Checking wether point P{} lies on {}: {}", p, toString(), result); //$NON-NLS-1$
		return result;
	}

	/**
	 * Displays a value table for the expression
	 *
	 * @param start - start value for x
	 * @param end - end value for x
	 * @param step - the step between x values
	 */
	public String[] table(double start, double end, double step) {

		logger.info("Calculating value table for {} from {} to {} with step {}...", toString(), start, end, step); //$NON-NLS-1$

		if (step == 0)
			throw new IllegalArgumentException(Strings.getStringAsHTML("Function.IAE_stepZero")); //$NON-NLS-1$
			
		final ArrayList<String> table = new ArrayList<>();

		final DoubleEvaluator evaluator = new DoubleEvaluator();
		final StaticVariableSet<Double> variables = new StaticVariableSet<>();

		if (start <= end)
			for (double x = start; x <= end; x += step) {
				variables.set("x", x); //$NON-NLS-1$
				table.add(name + "(" + x + ") = " //$NON-NLS-1$//$NON-NLS-2$
						+ UtilityLogic.roundDouble(evaluator.evaluate(expression, variables), 3));
			}
		else
			for (double x = start; x >= end; x -= step) {
				variables.set("x", x); //$NON-NLS-1$
				table.add(name + "(" + x + ") = " //$NON-NLS-1$//$NON-NLS-2$
						+ UtilityLogic.roundDouble(evaluator.evaluate(expression, variables), 3));
			}

		logger.info("...finished calulating value table"); //$NON-NLS-1$

		return table.toArray(new String[table.size()]);
	}

	/**
	 * Mirror a function on the x-axis
	 *
	 * @return the new function, null if something went wrong in deepCopy()
	 */
	public Function mirrorX() {
		logger.info("Creating a function from {} that is mirrored on the x-axis", toString()); //$NON-NLS-1$
		try {
			final Function copy = deepCopy();
			if (copy.getExpression().startsWith("-(") && copy.getExpression().endsWith(")")) { //$NON-NLS-1$ //$NON-NLS-2$
				copy.setExpression(copy.getExpression().substring(2, copy.getExpression().length() - 1));
				copy.setExpressionRounded(
						copy.getExpressionRounded().substring(2, copy.getExpressionRounded().length() - 1));
			} else {
				copy.setExpression("-(" + copy.getExpression() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
				copy.setExpressionRounded("-(" + copy.getExpressionRounded() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
				copy.setName(Controller.getInstance().getLogicController().getNextName());
			}
			return copy;
		} catch (final IOException e) {
			logger.error("Caught IOException when creating a deep copy of the original function {}: ", toString(), e); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Mirror a function on the y-axis
	 *
	 * @return the new function, null if something went wrong in deepCopy()
	 */
	public Function mirrorY() {
		logger.info("Creating a function from {} that is mirrored on the y-axis", toString()); //$NON-NLS-1$
		try {
			final Function copy = deepCopy();

			if (copy.getExpression().trim().equals("x")) { //$NON-NLS-1$
				copy.setExpression("-x"); //$NON-NLS-1$
				copy.setExpressionRounded("-x"); //$NON-NLS-1$
			} else {

				boolean endsWithX = false;
				if (copy.getExpression().endsWith("x")) //$NON-NLS-1$
					endsWithX = true;

				final String[] splitX = copy.getExpression().split("x"); //$NON-NLS-1$
				final String[] splitXRounded = copy.getExpressionRounded().split("x"); //$NON-NLS-1$

				copy.setExpression(splitX[0]);
				copy.setExpressionRounded(splitXRounded[0]);

				for (int i = 1; i < splitX.length; i++) {
					copy.setExpression(copy.getExpression() + "(-x)" + splitX[i]); //$NON-NLS-1$
					copy.setExpressionRounded(copy.getExpressionRounded() + "(-x)" + splitXRounded[i]); //$NON-NLS-1$
				}

				if (endsWithX) {
					copy.setExpression(copy.getExpression() + "(-x)"); //$NON-NLS-1$
					copy.setExpressionRounded(copy.getExpressionRounded() + "(-x)"); //$NON-NLS-1$
				}
			}

			copy.setName(Controller.getInstance().getLogicController().getNextName());
			return copy;
		} catch (final IOException e) {
			logger.error("Caught IOException when creating a deep copy of the original function {}: ", toString(), e); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Mirror a function on the x-axis and y-axis => rotate it around the origin
	 *
	 * @return the new function, null if something went wrong in deepCopy()
	 */
	public Function mirrorOrigin() {
		logger.info("Creating a function from {} that is mirrored on the x-axis and y-axis", toString()); //$NON-NLS-1$
		return mirrorX().mirrorY();
	}

	/**
	 * Creates a deep copy of the function using serialization and deserialization
	 *
	 * @return the copy of the function, null if something went wrong
	 * @throws IOException
	 */
	public Function deepCopy() throws IOException {
		logger.info("Creating a deep copy of the function {} ...", toString()); //$NON-NLS-1$
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(baos);
			out.writeObject(this);

			final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			in = new ObjectInputStream(bais);
			final Object copy = in.readObject();

			logger.info("... copied version: {}", copy); //$NON-NLS-1$
			
			if (this instanceof ExponentialFunction)
				return (ExponentialFunction) copy;
			if (this instanceof LinearFunction)
				return (LinearFunction) copy;
			return (Function) copy;
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Caught {} when creating a deep copy of the original function {}: ", e.getClass().getName(), //$NON-NLS-1$
					toString(), e);
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
		if (!(obj instanceof Function))
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
