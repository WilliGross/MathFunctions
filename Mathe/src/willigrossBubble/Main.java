package willigrossBubble;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Main {

	/**A utility array representing the alphabet */
	public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	/**A list where all functions are stored */
	private ArrayList<Function> functions = new ArrayList<Function>();
	
	/**A boolean value that represents the programs state */
	private static boolean running;

	
	/**
	 * The main method: creates a new instance of Main and keeps the program running
	 * @param args
	 */
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

	/**
	 * The constructor that calls the menu
	 */
	public Main() {
		
		menu();
	}

	
	/**
	 * Displays the menu and calls selected tasks
	 */
	private void menu() {

		String programMode = JOptionPane.showInputDialog("What would you like to do? \n"
				+ "(type a function, create an exponential function through 2 points, load a previous function, close)");

		if (programMode != null) {

			programMode.toLowerCase();

			if (programMode.contains("type") || programMode.contains("1")) {
				typeFunction();
			}

			if (programMode.contains("exp") || programMode.contains("2")) {
				createExponentialFunction();
			}

			if (programMode.contains("load") || programMode.contains("prev") || programMode.contains("3")) {
				showAndSelectPreviousFunctions();
			}

			if (programMode.contains("close") || programMode.contains("exit") || programMode.equals("") || programMode.contains("0")) {
				running = false;
			} else {
				menu();
			}

		} else {
			JOptionPane.showMessageDialog(null, "No operation entered! Exiting program!");
			running = false;
		}
	}

	/**
	 * Displays a menu for a specific function
	 * @param function - the function to interact with
	 */
	private void functionActionsMenu(Function function) {
	
		String action = JOptionPane.showInputDialog("Would you like to calculate a value table "
				+ "or check if a specified point lies on your functions graph?");
	
		if (action != null) {
	
			action.toLowerCase();
	
			if (action.contains("table") || action.contains("1"))
				valueTable(function);
	
			if (action.contains("check") || action.contains("point") || action.contains("2"))
				checkPointOnGraph(function);
		}
	
	}

	
	/**
	 * Displays previous functions and lets the user select one; calls the function actions menu
	 */
	private void showAndSelectPreviousFunctions() {

		//show prev functions
		String prevFunctions = "The functions you've previously entered: \n\n";

		for (int i = 0; i < functions.size(); i++) {
			if ((i + 5) <= 25) {
				prevFunctions += alphabet[i + 5] + "(x) = " + functions.get(i) + "\n";
			} else {
				JOptionPane.showMessageDialog(null, "You have to many functions! We can't display them all!");
			}
		}

		JOptionPane.showMessageDialog(null, prevFunctions);

		//select prev functions
		String seletion = JOptionPane.showInputDialog("Which function would you like to select? (enter its letter)");

		if (seletion.charAt(0) - 97 - 5 > functions.size() - 1) { //test if that function exists
			JOptionPane.showMessageDialog(null, "The requested function is not available!");
			showAndSelectPreviousFunctions();
		} else {
			functionActionsMenu(functions.get(seletion.charAt(0) - 97 - 5)); //ASCII value of a = 97 ; -5 as functions start with f
		}
	}

	/**
	 * Displays a value table for a function
	 * @param function - the function the value table should be calculated for
	 */
	private void valueTable(Function function) {

		String parameters = JOptionPane.showInputDialog("Enter start and end value for x and step , seperated by spaces: ");
		String[] params = parameters.split(" ");

		if (params.length < 3 || params.length > 3) { //check if there are 3 arguments
			JOptionPane.showMessageDialog(null, "Please enter 3 arguments!");
			valueTable(function);
		} else {
			function.table(Double.parseDouble(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]));
		}
	}

	
	/**
	 * Checks if a specified point lies on the graph
	 * @param function - the function whose graph should be checked
	 */
	private void checkPointOnGraph(Function function) {
		
		Point p = new Point(readDoubleFromStringInput("x coordinate of P: " ), readDoubleFromStringInput("y coordinate of P: " ));

		boolean onGraph = function.testPointOnGraph(p);

		if (onGraph) //TODO maybe use ? : operator
			JOptionPane.showMessageDialog(null, "The point lies on your function's graph");
		else
			JOptionPane.showMessageDialog(null, "The point does not lie on your function's graph");
	}

	
	/**
	 * Manually enter a function
	 */
	private void typeFunction() {

		String expression = JOptionPane.showInputDialog("Please enter your function: f(x) = ");

		functions.add(new Function());
		functions.get(functions.size() - 1).setExpression(expression);

		functionActionsMenu(functions.get(functions.size() - 1));
	}

	
	/**
	 * Create an exponential function by specifying two points
	 */
	private void createExponentialFunction() {

		functions.add(new ExponentialFunction());

		Point p = new Point(readDoubleFromStringInput("x coordinate of P: " ), readDoubleFromStringInput("y coordinate of P: " ));
		Point q = new Point(readDoubleFromStringInput("x coordinate of Q: " ), readDoubleFromStringInput("y coordinate of Q: " ));

		((ExponentialFunction) functions.get(functions.size() - 1)).createThroughPoints(p, q);

		JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));

		functionActionsMenu(functions.get(functions.size() - 1));
	}


	/**
	 * A utility method that converts a string expression into a double value
	 * @param displayMessage - the message that should be displayed when the user needs to enter the expression
	 * @return the calculated double value
	 */
	private double readDoubleFromStringInput(String displayMessage) {

		DoubleEvaluator evaluator = new DoubleEvaluator();
		String expression = JOptionPane.showInputDialog(displayMessage);

		return evaluator.evaluate(expression);
	}

}
