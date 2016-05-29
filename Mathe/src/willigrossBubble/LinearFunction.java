package willigrossBubble;

import javax.swing.JOptionPane;

public class LinearFunction extends Function {

	private static final long serialVersionUID = 1L;

	private LinearFunction(String expression) {
		super(expression);
	}
	
	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 */
	public static LinearFunction createThroughPoints(Point p, Point q) {
		
		String expression = "";
		
		if (p.getX() == q.getX() && !p.equals(q)) {
			JOptionPane.showMessageDialog(null, "There is no function f(x) for a vertical straight line!");
			return null;
		}
			
		
		double m = (q.getY() - p.getY()) / (q.getX() - p.getX());
		
		if (Double.isNaN(m))
			m = 0;
		
		double t = p.getY() - m * p.getX();
		
		
		if ( m != 0) {
			if (m - (int) m == 0)
				expression += (m != 1.0) ? (int) m + " * x" : "x"; 
			else
				expression += (m != 1.0) ? Function.roundDouble(m, 3) + " * x" : "x";
		}
		
		if (!expression.equals("") && t != 0)
			expression += " + ";
			
		if (t - (int) t == 0)
			expression += (t != 0.0) ? (int) t : "";
		else
			expression += (t != 0.0) ? Function.roundDouble(t, 3) : "";
		return new LinearFunction(expression);
	}


}
