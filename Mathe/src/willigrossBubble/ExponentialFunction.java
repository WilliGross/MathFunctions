package willigrossBubble;

public class ExponentialFunction extends Function {

	/**
	 * Create a function whose graph runs through two given points
	 * @param p - point 1
	 * @param q - point 2
	 */
	public void createThroughPoints(Point p, Point q) {
		double a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		double b = p.getY() / Math.pow(a, p.getX());

		if (Double.isNaN(a)) {
			a = Math.pow(p.getY(), 1 / p.getX());
			b = 1;
			
			if (a - (int) a == 0)
				expression += (int) a;
			else
				expression += Function.roundDouble(a, 3);
			
			return;
		}
		

		if ( a == 0 || b == 0)
			expression = "0";
		else {

			if (b - (int) b == 0)
				expression += (b != 1.0) ? (int) b : ""; 
			else
				expression += (b != 1.0) ? Function.roundDouble(b, 3) : "";

			if (!expression.equals("") && a!= 1.0)
				expression += " * ";
				
			if (a - (int) a == 0)
				expression += (a != 1.0) ? (int) a + "^x": "";
			else
				expression += (a != 1.0) ? Function.roundDouble(a, 3) + "^x": "";
			
			
			if (expression.equals(""))
				expression = "1";

		}

	}

}
