package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import willigrossBubble.gui.customComponents.panels.CenterPanel;
import willigrossBubble.gui.customComponents.panels.PanelCreateExponential;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction;
import willigrossBubble.gui.customComponents.panels.PanelCreateLinear;
import willigrossBubble.gui.customComponents.panels.PanelIntersection;
import willigrossBubble.gui.customComponents.panels.PanelLoadFunction;
import willigrossBubble.gui.customComponents.panels.PanelMain;
import willigrossBubble.gui.customComponents.panels.PanelNavigation;
import willigrossBubble.gui.customComponents.panels.PanelTypeFunction;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	public static FrameMain instance;
	private Container c;
	private CenterPanel panelCenter;
	private PanelNavigation panelSouth;
	
	public FrameMain() {
		
		setTitle("Mathe");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		
		
		c = getContentPane();
		

		panelSouth = new PanelNavigation();
		c.add(panelSouth, BorderLayout.SOUTH);
		panelCenter = new PanelMain();
		panelMain();
	}
	
	public static void main(String[] args) {
		instance = new FrameMain();
	}

	public void back() {
		panelCenter.back();
	}

	public void panelMain() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelMain(), BorderLayout.CENTER);
		panelSouth.setVisibility(false, false);
		panelCenter.revalidate();
	}

	public void panelCreateFunction() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelCreateFunction(), BorderLayout.CENTER);
		panelSouth.setVisibility(true, false);
		panelCenter.revalidate();
	}

	public void panelLoadFunction() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelLoadFunction(), BorderLayout.CENTER);
		panelSouth.setVisibility(true, false);
		panelCenter.revalidate();
	}

	public void panelIntersection() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelIntersection(), BorderLayout.CENTER);
		panelSouth.setVisibility(true, false);
		panelCenter.revalidate();
	}
	
	public void typeFunction() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelTypeFunction(), BorderLayout.CENTER);
		panelSouth.setVisibility(true, true);
		panelCenter.revalidate();
	}

	public void createLinear() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelCreateLinear(), BorderLayout.CENTER);
		panelSouth.setVisibility(true, true);
		panelCenter.revalidate();
	}

	public void createExponential() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelCreateExponential(), BorderLayout.CENTER);
		panelSouth.setVisibility(true, true);
		panelCenter.revalidate();
	}

}
