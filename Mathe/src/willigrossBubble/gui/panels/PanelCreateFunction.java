package willigrossBubble.gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCreateFunction extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel desc;
	
	public PanelCreateFunction() {
		System.out.println("Konstruktor!");
		desc = new JLabel("How would you like to create your function?");
		add(desc);
	}
	
}
