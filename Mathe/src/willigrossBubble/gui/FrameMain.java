package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import willigrossBubble.gui.panels.PanelCreateFunction;
import willigrossBubble.gui.panels.PanelIntersection;
import willigrossBubble.gui.panels.PanelLoadFunction;
import willigrossBubble.gui.panels.PanelMain;
import willigrossBubble.gui.panels.PanelTypeFunction;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	public static FrameMain instance;
	private Container c;
	private JPanel panel;
	
	public FrameMain() {
		
		setTitle("Mathe");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		
		
		c = getContentPane();
		
		panel = new JPanel();
		panelMain();
	}
	
	public static void main(String[] args) {
		instance = new FrameMain();
	}

	public void panelMain() {
		c.remove(panel);
		c.add(panel = new PanelMain(), BorderLayout.CENTER);
		panel.revalidate();
	}

	public void panelCreateFunction() {
		c.remove(panel);
		c.add(panel = new PanelCreateFunction(), BorderLayout.CENTER);
		panel.revalidate();
	}

	public void panelLoadFunction() {
		c.remove(panel);
		c.add(panel = new PanelLoadFunction(), BorderLayout.CENTER);
		panel.revalidate();
	}

	public void panelIntersection() {
		c.remove(panel);
		c.add(panel = new PanelIntersection(), BorderLayout.CENTER);
		panel.revalidate();
	}
	
	public void typeFunction() {
		c.remove(panel);
		c.add(panel = new PanelTypeFunction(), BorderLayout.CENTER);
		panel.revalidate();
	}

}
