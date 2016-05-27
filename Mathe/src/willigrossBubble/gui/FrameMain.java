package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import willigrossBubble.gui.panels.PanelCreateFunction;
import willigrossBubble.gui.panels.PanelIntersection;
import willigrossBubble.gui.panels.PanelLoadFunction;
import willigrossBubble.gui.panels.PanelMain;

public class FrameMain extends JFrame{

	private static final long serialVersionUID = 1L;
	public static FrameMain instance;
	private Container c;
	
	public FrameMain() {
		instance = this;
		
		setTitle("Mathe");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		
		
		c = getContentPane();
		
		panelMain();
	}
	
	public static void main(String[] args) {
		new FrameMain();
	}

	public void panelMain() {
		c.add(new PanelMain(), BorderLayout.CENTER);
	}

	public void panelCreateFunction() {
		System.out.println("Methode in framemain!");
		c.add(new PanelCreateFunction(), BorderLayout.CENTER);
		revalidate();
	}

	public void panelLoadFunction() {
		c.add(new PanelLoadFunction(), BorderLayout.CENTER);
	}

	public void panelIntersection() {
		c.add(new PanelIntersection(), BorderLayout.CENTER);
	}

}
