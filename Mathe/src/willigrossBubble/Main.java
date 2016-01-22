package willigrossBubble;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Main {

	private ArrayList<Function> functions = new ArrayList<Function>();
	private static boolean running;

	public static void main(String[] args) {

		new Main();


		//keep program running
		while (running == true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

	public Main() {
		menu();
	}

	private void menu() {

		//TODO add option to show a list of previous functions
		
		String programMode = JOptionPane.showInputDialog("What would you like to do? \n"
				+ "(type a function, create an exponential function through 2 points, load a previous function, close)");

		if (programMode != null) {

			programMode.toLowerCase();

			if (programMode.contains("type") || programMode.contains("1"))
				typeFunction();

			if (programMode.contains("exp") || programMode.contains("2")) {
				createExponentialFunction();
			}

			if (programMode.contains("load") || programMode.contains("prev") || programMode.contains("3")) {
				functionActionsMenu(); //TODO specify function
			}

			if (programMode.contains("close") || programMode.contains("exit") || programMode.contains("0")) {
				running = false;
			}

		}
	}

	private void functionActionsMenu() {

		//TODO function as parameter
		String action = JOptionPane.showInputDialog("Would you like to calculate a value table "
				+ "or check if a specified point lies on your functions graph?");

		if (action != null) {

			action.toLowerCase();

			if (action.contains("table") || action.contains("1"))
				valueTable();

			if (action.contains("check") || action.contains("point") || action.contains("2"))
				checkPointOnGraph();
		}

	}

	private void valueTable() {

		String parameters = JOptionPane.showInputDialog("Enter start and end value for x and step , seperated by spaces: ");
		String[] params = parameters.split(" ");

		if (params.length < 3 || params.length > 3) {
			JOptionPane.showMessageDialog(null, "Please enter 3 arguments!");
			valueTable();
		} else {
			functions.get(functions.size()).table(Double.parseDouble(params[0]), 
					Double.parseDouble(params[1]), Double.parseDouble(params[2]));
		}
	}

	private void checkPointOnGraph() {
		Point p = new Point(readDoubleFromStringInput("x Koordinate von P: " ), readDoubleFromStringInput("y Koordinate von P: " ));
		
		boolean onGraph = functions.get(functions.size()).testPointOnGraph(p);
		
		if (onGraph)
			JOptionPane.showMessageDialog(null, "The point lies on your function's graph");
		else
			JOptionPane.showMessageDialog(null, "The point does not lie on your function's graph");
	}

	private void typeFunction() {

		String expression = JOptionPane.showInputDialog("Please enter your function: f(x) = ");

		functions.add(new Function());
		functions.get(functions.size()).setExpression(expression);

		functionActionsMenu();
	}

	private void createExponentialFunction() {

		functions.add(new ExponentialFunction());

		Point p = new Point(readDoubleFromStringInput("x Koordinate von P: " ), readDoubleFromStringInput("y Koordinate von P: " ));
		Point q = new Point(readDoubleFromStringInput("x Koordinate von Q: " ), readDoubleFromStringInput("y Koordinate von Q: " ));

		((ExponentialFunction) functions.get(functions.size())).createThroughPoints(p, q);

		JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size()));
		
		functionActionsMenu();
	}


	private  double readDoubleFromStringInput(String displayMessage) {

		DoubleEvaluator evaluator = new DoubleEvaluator();
		String expression = JOptionPane.showInputDialog(displayMessage);

		return evaluator.evaluate(expression);
	}

}
