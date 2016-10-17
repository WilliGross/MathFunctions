package willigross.gui.customComponents.buttons;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

public class CustomButton extends JButton {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor that creates a new button with some properties already set
	 *
	 * @param s
	 *            the text to display on the button
	 */
	public CustomButton(String s) {
		setText(s);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLUE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	/** Creates a custom button with the text "Button" */
	public CustomButton() {
		this("Button"); //$NON-NLS-1$
	}

}
