package willigrossBubble;

import javax.swing.JOptionPane;

import com.fathzer.soft.javaluator.DoubleEvaluator;




public class ExponentialFunktionDurch2Punkte {
	
	public void run() {
		
		Point p = new Point(readDoubleFromStringInput("x Koordinate von P: " ), readDoubleFromStringInput("y Koordinate von P: " ));
		
		Point q = new Point(readDoubleFromStringInput("x Koordinate von Q: " ), readDoubleFromStringInput("y Koordinate von Q: " ));
		
		double a = Math.pow(q.getY() / p.getY(), 1 / (q.getX() - p.getX()));
		double b = p.getY() / Math.pow(a, p.getX());
		
		JOptionPane.showMessageDialog(null, "f(x) = " + b + " * " + a + "^x" );
	}
	
	
	
	private  double readDoubleFromStringInput(String displayMessage) {
		
		DoubleEvaluator evaluator = new DoubleEvaluator();
		String expression = JOptionPane.showInputDialog(displayMessage);
		
		return evaluator.evaluate(expression);
	}

}
