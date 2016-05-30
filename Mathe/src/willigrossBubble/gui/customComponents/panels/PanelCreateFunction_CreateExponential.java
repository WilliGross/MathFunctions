package willigrossBubble.gui.customComponents.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;


public class PanelCreateFunction_CreateExponential extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField p1x, p1y, p2x, p2y;
	private JLabel p1, p2, error, function;
	private CustomButtonSmall go;
	
	public PanelCreateFunction_CreateExponential() {
		
		p1 = new JLabel("Point 1:", SwingConstants.RIGHT);
		p2 = new JLabel("Point 2:", SwingConstants.RIGHT);
		error = new JLabel();
		function = new JLabel();
		
		p1x = new JTextField("x-coordinate");
		p1x.setBounds(100, 40, 400, 30);
		add(p1x);
		p1y = new JTextField("y-coordinate");
		p1y.setBounds(100, 40, 400, 30);
		add(p1y);
		p2x = new JTextField("x-coordinate");
		p2y = new JTextField("y-coordinate");
		
		go = new CustomButtonSmall("Go");
		
	}
	
}
