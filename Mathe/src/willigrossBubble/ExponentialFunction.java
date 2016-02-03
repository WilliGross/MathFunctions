package willigrossBubble;

import javax.swing.JOptionPane;

public class ExponentialFunction extends Function {

	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 */
	public boolean createThroughPoints(Point p, Point q) {
		
		double a, b;
		
		if (p.equals(q)) {
			a = Math.pow(p.getY(), 1 / p.getX());
			
			if (a - (int) a == 0)
				expression += (int) a + "^x";
			else
				expression += Function.roundDouble(a, 3) + "^x";
			
			return true;
		}
		
		
		if (p.getX() == q.getX() || p.getY() == q.getY()) {
			JOptionPane.showMessageDialog(null, "Invalid points, exponential functions' graphs are never perfectly horizontal or vertical!");
			expression = "Invalid points!";
			return false;
		}
		
		
		a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		b = p.getY() / Math.pow(a, p.getX());
		

		if ( a == 0 || b == 0)
			expression = "0";
		else {

			if (b - (int) b == 0)
				expression += (b != 1.0) ? (int) b + " * " : ""; 
			else
				expression += (b != 1.0) ? Function.roundDouble(b, 3) + " * " : "";

				
			if (a - (int) a == 0)
				expression += (int) a + "^x";
			else
				expression += Function.roundDouble(a, 3) + "^x";
			
			
		}
		return true;

	}

}
