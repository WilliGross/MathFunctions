package willigrossBubble;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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

		String programMode = JOptionPane.showInputDialog("What would you like to do? \n"
				+ "(create an exponential function, future stuff, close)");

		if (programMode != null) {

			programMode.toLowerCase();

			if (programMode.contains("exp") || programMode.contains("1")) {
				functions.add(new ExponentialFunction());
			}
			
			if (programMode.contains("fut") || programMode.contains("2")) {
				JOptionPane.showMessageDialog(null, "As the name suggests these functions are not implemented yet!");
			}
			
			if (programMode.contains("close") || programMode.contains("exit") || programMode.contains("0")) {
				running = false;
			}
			
		}
	}

}
