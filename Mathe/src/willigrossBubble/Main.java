package willigrossBubble;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Main {

	/**A utility array representing the alphabet */
	public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	/**A list where all functions are stored */
	private ArrayList<Function> functions = new ArrayList<Function>();


	/**
	 * The main method: creates a new instance of Main and keeps the program running
	 * @param args
	 */
	public static void main(String[] args) {

		new Main();

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
				+ "(CREATE a function, LOAD a previous function, calculate the INTERSECTION of two functions, CLOSE)");

		if (programMode != null) {

			programMode.toLowerCase();


			if (programMode.contains("create") || programMode.contains("1")) {
				createFunctionsMenu();
			}

			if (programMode.contains("load") || programMode.contains("prev") || programMode.contains("2")) {
				showAndSelectPreviousFunctions();
			}
			
			if (programMode.contains("inter") || programMode.contains("3")) {
				calcIntersection();
			}

			if (programMode.contains("close") || programMode.contains("exit") 
					|| programMode.equals("") || programMode.contains("0") || programMode.contains("4")) {
			} else {
				menu();
			}

		} else {
			JOptionPane.showMessageDialog(null, "No operation entered! Exiting program!");
		}
	}


	/**
	 * Displays a menu for creating functions
	 */
	private void createFunctionsMenu() {

		String functionType = JOptionPane.showInputDialog("How would you like to create your function? \n"
				+ "(TYPE, create LINEAR f. through 2 points, create EXPONENTIAL f. through 2 points, go BACK to main menu)");

		if (functionType != null) {

			functionType.toLowerCase();

			if (functionType.contains("type") || functionType.contains("1")) {
				typeFunction();
			}

			if (functionType.contains("lin") || functionType.contains("2")) {
				createLinearFunction();
			}

			if (functionType.contains("exp") || functionType.contains("3")) {
				createExponentialFunction();
			}



			//if nothing matches go back to main menu automatically due to recursive method call in menu()

		} else {
			JOptionPane.showMessageDialog(null, "Nothing entered, going back to main menu!");
			//going back automatically due to recursive method call in menu()
		}

	}

	/**
	 * Displays a menu for a specific function
	 * @param function - the function to interact with
	 */
	private void functionActionsMenu(Function function) {

		String action = JOptionPane.showInputDialog("Would you like to calculate a VALUE TABLE "
				+ "or check if a specified POINT lies on your function's graph?" 
				+ "\nYou can also create a MIRRORED version of your function (type X, Y or origin)");

		if (action != null) {

			action.toLowerCase();

			if (action.contains("val") || action.contains("table") || action.contains("1"))
				valueTable(function);

			if (action.contains("check") || action.contains("point") || action.contains("2"))
				checkPointOnGraph(function);

			if (action.contains("x") || action.contains("3")) {
				functions.add(function.mirrorX());
				JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));
				functionActionsMenu(functions.get(functions.size() - 1));
			}

			if (action.contains("y") || action.contains("4")) {
				functions.add(function.mirrorY());
				JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));
				functionActionsMenu(functions.get(functions.size() - 1));
			}

			if (action.contains("ori") || action.contains("5")) {
				functions.add(function.mirrorOrigin());
				JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));
				functionActionsMenu(functions.get(functions.size() - 1));
			}

			if (action.contains("mirr")) {
				JOptionPane.showMessageDialog(null, "Please enter \"x\", \"y\" or \"origin\"!");
				functionActionsMenu(function);
			}

		}

	}


	/**
	 * Displays previous functions and lets the user select one; calls the function actions menu
	 */
	private void showAndSelectPreviousFunctions() {

		if (functions.size() > 0) {
			//show prev functions
			String prevFunctions = "The functions you've previously entered: \n\n";//TODO use stringbuffer

			for (int i = 0; i < functions.size(); i++) {
				if ((i + 5) <= 25) {
					prevFunctions += alphabet[i + 5] + "(x) = " + functions.get(i) + "\n";
				} else {
					JOptionPane.showMessageDialog(null, "You have to many functions! We can't display them all!");
				}
			}

			JOptionPane.showMessageDialog(null, prevFunctions);

			//select prev functions

			String selection;

			if (functions.size() == 1) {
				selection = "f";
			} else {
				selection = JOptionPane.showInputDialog("Which function would you like to select? (enter its letter)");
			}

			if (selection == null || selection.equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing entered, going bach to main menu!");
				return;
			}


			if (selection.charAt(0) - 97 - 5 > functions.size() - 1) { //test if that function exists
				JOptionPane.showMessageDialog(null, "The requested function is not available!");
				showAndSelectPreviousFunctions();
			} else {
				functionActionsMenu(functions.get(selection.charAt(0) - 97 - 5)); //ASCII value of a = 97 ; -5 as functions start with f
			}
		} else {
			JOptionPane.showMessageDialog(null, "There are no previous functions saved! Create one first!");
		}

	}

	/**
	 * Displays a value table for a function
	 * @param function - the function the value table should be calculated for
	 */
	private void valueTable(Function function) {

		String parameters = JOptionPane.showInputDialog("Enter START and END value for x and STEP, seperated by spaces: ");
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

		Point p = new Point(readDoubleFromStringInput("x coordinate of point P: " ), readDoubleFromStringInput("y coordinate of point P: " ));

		boolean onGraph = function.testPointOnGraph(p);

		if (onGraph)
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

		Point p = new Point(readDoubleFromStringInput("x coordinate of point P: " ), readDoubleFromStringInput("y coordinate of point P: " ));
		Point q = new Point(readDoubleFromStringInput("x coordinate of point Q: " ), readDoubleFromStringInput("y coordinate of point Q: " ));

		boolean success = ((ExponentialFunction) functions.get(functions.size() - 1)).createThroughPoints(p, q);

		if (success) {
			JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));

			functionActionsMenu(functions.get(functions.size() - 1));
		}
	}


	/**
	 * Create an linear function by specifying two points
	 */
	private void createLinearFunction() {

		functions.add(new LinearFunction());

		Point p = new Point(readDoubleFromStringInput("x coordinate of point P: " ), readDoubleFromStringInput("y coordinate of point P: " ));
		Point q = new Point(readDoubleFromStringInput("x coordinate of point Q: " ), readDoubleFromStringInput("y coordinate of point Q: " ));

		boolean success = ((LinearFunction) functions.get(functions.size() - 1)).createThroughPoints(p, q);


		if (success) {
			JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));

			functionActionsMenu(functions.get(functions.size() - 1));
		}
	}

	private void calcIntersection() {
		
		//TODO DON'T COPY THE WHOLE showAndSelectPreviousFunctions() METHOD!!!
		if (functions.size() > 0) {
			//show prev functions
			String prevFunctions = "The functions you've previously entered: \n\n"; //TODO use stringbuffer

			for (int i = 0; i < functions.size(); i++) {
				if ((i + 5) <= 25) {
					prevFunctions += alphabet[i + 5] + "(x) = " + functions.get(i) + "\n";
				} else {
					JOptionPane.showMessageDialog(null, "You have to many functions! We can't display them all!");
				}
			}

			JOptionPane.showMessageDialog(null, prevFunctions);

			//select prev functions

			String selection1, selection2;

			if (functions.size() == 1) {
				selection1 = "f";
				selection2 = "f";
			} else {
				selection1 = JOptionPane.showInputDialog("Which function would you like to select as function 1? (enter its letter)");
				selection2 = JOptionPane.showInputDialog("Which function would you like to select as function 2? (enter its letter)");
			}

			if (selection1 == null || selection1.equals("") || selection2 == null || selection2.equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing entered, going bach to main menu!");
				return;
			}


			if (selection1.charAt(0) - 97 - 5 > functions.size() - 1 || selection2.charAt(0) - 97 - 5 > functions.size() - 1) { //test if that function exists
				JOptionPane.showMessageDialog(null, "The requested function is not available!");
				showAndSelectPreviousFunctions();
			} else {
				Intersection intersection = new Intersection(functions.get(selection1.charAt(0) - 97 - 5), functions.get(selection2.charAt(0) - 97 - 5)); //ASCII value of a = 97 ; -5 as functions start with f
				JOptionPane.showMessageDialog(null, "The functions " + functions.get(selection1.charAt(0) - 97 - 5) + " and " + functions.get(selection2.charAt(0) - 97 - 5) + " intersect in the point: " + intersection);
			}
		} else {
			JOptionPane.showMessageDialog(null, "There are no previous functions saved! Create one first!");
		}
		
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

