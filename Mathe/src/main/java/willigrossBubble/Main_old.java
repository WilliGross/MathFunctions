package willigrossBubble;

//import java.awt.Color;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import javax.swing.JOptionPane;
//
//import com.blogspot.debukkitsblog.Util.FileStorage;
//import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Main_old {

//	/**A utility array representing the alphabet */
//	public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//
//	/**Location where file is executed from*/
//	public static URL location = Main_old.class.getProtectionDomain().getCodeSource().getLocation();
//
//	/**A list where all functions are stored */
//	private static ArrayList<Function> functions = new ArrayList<>();
//	
//	public static int zahl = 0; 
//	public static String s = "";
//
//
//
//	/**
//	 * The main method: creates a new instance of Main_old and keeps the program running
//	 * @param args
//	 */
//	@SuppressWarnings("unused")
//	public static void main(String[] args) {
//
//		new Main_old();
//
//	} 
//
//	/**
//	 * The constructor that calls the menu
//	 */
//	@SuppressWarnings("unused")
//	public Main_old() {
//		boolean success = loadFunctions();
//		if (success)
//			new Frame();
//		else
//			JOptionPane.showMessageDialog(null, "Error while loading file!");
//	}
//
//	/**
//	 * Loads all the functions from the save file and adds them to the functions list
//	 * @return true if the action was successful
//	 */
//	private static boolean loadFunctions() {
//		FileStorage fs = null;
//		String key;
//		Function function;
//
//		try {
//			fs = new FileStorage(new File(location.getFile().substring(0, location.getFile().lastIndexOf('/') + 1) + "Functions.dat"));
//		} catch (IllegalArgumentException | IOException e) {
//			JOptionPane.showMessageDialog(null, "Error with file!");
//			e.printStackTrace();
//			return false;
//		}
//
//		for (int i = 5; i < alphabet.length; i++) {
//			key = "" + alphabet[i];
//			function = (Function) fs.get(key);
//			if (function != null)
//				functions.add(function);
//		}
//
//		return true;
//	}
//
//	/**
//	 * Displays a menu for a specific function
//	 * @param function - the function to interact with
//	 */
//	@SuppressWarnings("hiding")
//	static void functionActionsMenu(Function function) {
//
//		/*String action = JOptionPane.showInputDialog("Would you like to calculate a VALUE TABLE "
//				+ "or check if a specified POINT lies on your function's graph?" 
//				+ "\nYou can also create a MIRRORED version of your function (type X, Y or origin)"
//				+ "\nWould you like to SAVE this function or REMOVE it from the file (if it's already saved)?");*/
//		if (zahl != 0) {
//			if (zahl == 1){
//				valueTable(function);
//				zahl = -1;
//
//			}
//			if (zahl == 2) {
//				checkPointOnGraph(function);
//				zahl = -1;
//			}
//			if (zahl == 3) {
//				functions.add(function.mirrorX());
//				zahl = -1;
//				Frame.frameMirror();
//				String s = "" + functions.get(functions.size() - 1);
//				Frame.framausgabemirror(s);
//				functionActionsMenu(functions.get(functions.size() - 1));
//			}
//
//			if (zahl == 4) {
//				functions.add(function.mirrorY());
//				zahl = -1;
//				Frame.frameMirror();
//				String s = "" + functions.get(functions.size() - 1);
//				Frame.framausgabemirror(s);
//				functionActionsMenu(functions.get(functions.size() - 1));
//			}
//			
//			if (zahl == 5) {
//				functions.add(function.mirrorOrigin());
//				zahl = -1;
//				Frame.frameMirror();
//				String s = "" + functions.get(functions.size() - 1);
//				Frame.framausgabemirror(s);
//				functionActionsMenu(functions.get(functions.size() - 1));
//			}
//
//			if (zahl == 6) {
//				functions.add(function.mirrorOrigin());
//				JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));
//				functionActionsMenu(functions.get(functions.size() - 1));
//				zahl = -1;
//			}
//
//			if (zahl == 7) {
//				save(function);
//				zahl = -1;
//			
//			}	
//			if (zahl == 8) {
//				removeFunctionFromFile(function);
//				zahl = -1;
//			}
//		}
//
//	}
//
//	/**
//	 * Removes a function from the save file but not from the functions list (-> effective after restart)
//	 * @param function - the function to remove
//	 */
//	private static void removeFunctionFromFile(Function function) {
//		FileStorage fs = null;
//		
//		try {
//			fs = new FileStorage(new File(location.getFile().substring(0, location.getFile().lastIndexOf('/') + 1) + "Functions.dat"));
//			Frame.label14.setText("Successfully removed your function");
//			Frame.label14.setForeground(Color.GRAY);
//			Frame.label14.setVisible(true);
//		} catch (IllegalArgumentException | IOException e) {
//			Frame.label14.setText("Error with file!");
//			Frame.label14.setForeground(Color.RED);
//			Frame.label14.setVisible(true);
//			e.printStackTrace();
//			return;
//		}
//		fs.remove("" + alphabet[functions.indexOf(function) + 5]);
//	}
//
//
//
//	/**
//	 * Save the function to Functions.dat file in the execution environment
//	 * @param function - the function to save
//	 */
//	private static void save(Function function) {
//		FileStorage fs = null;
//		String key;
//		try {
//			fs = new FileStorage(new File(location.getFile().substring(0, location.getFile().lastIndexOf('/') + 1) + "Functions.dat"));
//			Frame.label14.setText("Successfully saved your function");
//			Frame.label14.setForeground(Color.GREEN);
//			Frame.label14.setVisible(true);
//		} catch (IllegalArgumentException | IOException e) {
//			Frame.label14.setText("Error when saving!");
//			Frame.label14.setForeground(Color.RED);
//			Frame.label14.setVisible(true);
//			e.printStackTrace();
//			return;
//		}
//
//		for (int i = 0; i < alphabet.length - 5; i++) {
//			key = "" + alphabet[i + 5];
//			if (! fs.hasKey(key)) {
//				fs.store(key, function);
//				break;
//			}
//		}
//	}
//
//	/**
//	 * Displays previous functions and lets the user select one; calls the function actions menu
//	 */
//	public static void showPreviousFunctions() {
//
//		if (functions.size() > 0) {
//			//show prev functions
//			String prevFunctions = "<html>";//TODO use stringbuffer
//
//			for (int i = 0; i < functions.size(); i++) {
//				if ((i + 5) <= 25) {
//					prevFunctions += alphabet[i + 5] + "(x) = " + functions.get(i) + "<p/>";
//				} else {
//					JOptionPane.showMessageDialog(null, "You have to many functions! We can't display them all!");
//				}
//			}
//			Frame.showAndSelectPrevious(prevFunctions);
//		}else {
//			JOptionPane.showMessageDialog(null, "There are no previous functions saved! Create one first!");
//			}
//	}
//	
//	public static String t = "";
//	
//	public static void selectPreviousFunctions() {
//		String selection;
//
//		if (functions.size() == 1) {
//			selection = "f";
//		} else {
//			selection = Frame.tfield7.getText();
//		}
//
//		if (selection == null || selection.equals("")) {
//			JOptionPane.showMessageDialog(null, "Nothing entered, going bach to main menu!");
//			return;
//		}
//
//
//		if (selection.charAt(0) - 97 - 5 > functions.size() - 1) { //test if that function exists
//			JOptionPane.showMessageDialog(null, "The requested function is not available!");
//			selectPreviousFunctions();
//		} else {
//			t = "" + functions.get(selection.charAt(0) - 97 - 5);
//			functionActionsMenu(functions.get(selection.charAt(0) - 97 - 5)); //ASCII value of a = 97 ; -5 as functions start with f
//			Frame.frameActionsMenu();
//		}
//	}
//
//	/**
//	 * Displays a value table for a function
//	 * @param function - the function the value table should be calculated for
//	 */
//	private static void valueTable(Function function) {
//
//		String[] params = {Frame.tfield2.getText(), Frame.tfield3.getText(), Frame.tfield4.getText()};
//
//		if (params.length < 3 || params.length > 3) { //check if there are 3 arguments
//			JOptionPane.showMessageDialog(null, "Please enter 3 arguments!");
//			valueTable(function);
//		} else {
//			function.table(Double.parseDouble(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]));
//		}
//	}
//
//
//	/**
//	 * Checks if a specified point lies on the graph
//	 * @param function - the function whose graph should be checked
//	 */
//	private static void checkPointOnGraph(Function function) {
//		try {
//			if (Frame.tfield5.getText().equals("")) {
//				throw new EingabeException("Please fill in the x-Coordinate of Point p!!");
//			}
//			if (Frame.tfield6.getText().equals("")) {
//				throw new EingabeException("Please fill in the y-coordinate of point p!!");
//			}
//			Point p = new Point(Double.parseDouble(Frame.tfield5.getText()), Double.parseDouble(Frame.tfield6.getText() ));
//
//			boolean onGraph = function.testPointOnGraph(p);
//
//			if (onGraph)
//				Frame.frameanzeigepoint(1);
//			else
//				Frame.frameanzeigepoint(2);
//			
//		}catch(EingabeException e) {
//			Frame.label13.setText(e.getMessage());
//			Frame.label13.setVisible(true);
//		}catch(NumberFormatException e) {
//			Frame.label13.setText("Ups! You've made an input error");
//			Frame.label13.setVisible(true);
//			System.err.println(e);
//		}catch(Exception e) {
//			Frame.label13.setText("Ups! You've made an input error!!");
//			Frame.label13.setVisible(true);
//			System.err.println(e);
//		}
//		
//	}
//
//
//	/**
//	 * Manually enter a function
//	 */
//	static void typeFunction(int i) {
//		
//		zahl = i;
//
//		String expression = Frame.tfield1.getText();
//
//		functions.add(new Function());
//		functions.get(functions.size() - 1).setExpression(expression);
//		
//		
//
//		functionActionsMenu(functions.get(functions.size() - 1));
//	}
//
//
//	/**
//	 * Create an exponential function by specifying two points
//	 */
//	@SuppressWarnings("unused")
//	private static void createExponentialFunction() {
//
//		functions.add(new ExponentialFunction());
//
//		Point p = new Point(readDoubleFromStringInput("x coordinate of point P: " ), readDoubleFromStringInput("y coordinate of point P: " ));
//		Point q = new Point(readDoubleFromStringInput("x coordinate of point Q: " ), readDoubleFromStringInput("y coordinate of point Q: " ));
//
//		boolean success = ((ExponentialFunction) functions.get(functions.size() - 1)).createThroughPoints(p, q);
//
//		if (success) {
//			JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));
//
//			functionActionsMenu(functions.get(functions.size() - 1));
//		}
//	}
//
//
//	/**
//	 * Create an linear function by specifying two points
//	 */
//	@SuppressWarnings("unused")
//	private static void createLinearFunction() {
//
//		functions.add(new LinearFunction());
//
//		Point p = new Point(readDoubleFromStringInput("x coordinate of point P: " ), readDoubleFromStringInput("y coordinate of point P: " ));
//		Point q = new Point(readDoubleFromStringInput("x coordinate of point Q: " ), readDoubleFromStringInput("y coordinate of point Q: " ));
//
//		boolean success = ((LinearFunction) functions.get(functions.size() - 1)).createThroughPoints(p, q);
//
//
//		if (success) {
//			JOptionPane.showMessageDialog(null, "Your function: f(x) = " + functions.get(functions.size() - 1));
//
//			functionActionsMenu(functions.get(functions.size() - 1));
//		}
//	}
//
//	static void calcIntersection() {
//
//		//TODO DON'T COPY THE WHOLE showAndSelectPreviousFunctions() METHOD!!!
//		if (functions.size() > 0) {
//			//show prev functions
//			String prevFunctions = "The functions you've previously entered: \n\n"; //TODO use stringbuffer
//
//			for (int i = 0; i < functions.size(); i++) {
//				if ((i + 5) <= 25) {
//					prevFunctions += alphabet[i + 5] + "(x) = " + functions.get(i) + "\n";
//				} else {
//					JOptionPane.showMessageDialog(null, "You have to many functions! We can't display them all!");
//				}
//			}
//
//			JOptionPane.showMessageDialog(null, prevFunctions);
//
//			//select prev functions
//
//			String selection1, selection2;
//
//			if (functions.size() == 1) {
//				selection1 = "f";
//				selection2 = "f";
//			} else {
//				selection1 = JOptionPane.showInputDialog("Which function would you like to select as function 1? (enter its letter)");
//				selection2 = JOptionPane.showInputDialog("Which function would you like to select as function 2? (enter its letter)");
//			}
//
//			if (selection1 == null || selection1.equals("") || selection2 == null || selection2.equals("")) {
//				JOptionPane.showMessageDialog(null, "Nothing entered, going bach to main menu!");
//				return;
//			}
//
//
//			if (selection1.charAt(0) - 97 - 5 > functions.size() - 1 || selection2.charAt(0) - 97 - 5 > functions.size() - 1) { //test if that function exists
//				JOptionPane.showMessageDialog(null, "The requested function is not available!");
//				showPreviousFunctions();
//			} else {
//				Intersection intersection = new Intersection(functions.get(selection1.charAt(0) - 97 - 5), functions.get(selection2.charAt(0) - 97 - 5)); //ASCII value of a = 97 ; -5 as functions start with f
//				JOptionPane.showMessageDialog(null, "The functions " + functions.get(selection1.charAt(0) - 97 - 5) + " and " + functions.get(selection2.charAt(0) - 97 - 5) + " intersect in the point: " + intersection);
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "There are no previous functions saved! Create one first!");
//		}
//
//	}
//
//	/**
//	 * A utility method that converts a string expression into a double value
//	 * @param displayMessage - the message that should be displayed when the user needs to enter the expression
//	 * @return the calculated double value
//	 */
//	private static double readDoubleFromStringInput(String displayMessage) {
//
//		DoubleEvaluator evaluator = new DoubleEvaluator();
//		String expression = JOptionPane.showInputDialog(displayMessage);
//
//		return evaluator.evaluate(expression);
//	}
//	
//	public static class EingabeException extends NumberFormatException {
//		private static final long serialVersionUID = 1L;
//		public EingabeException() {
//			super();
//		}
//		public EingabeException(String s){
//			super(s);
//		}
//	}

}

