package willigrossBubble.gui;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

public class CustomButtonMain extends JButton {
		
	private static final long serialVersionUID = 1L;

	public CustomButtonMain(String s) {
		super(s);
		setSize(200, 30);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLUE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public CustomButtonMain() {
		this("");
	}
}
