package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import willigrossBubble.MainLogic;
import willigrossBubble.gui.customComponents.panels.CenterPanel;
import willigrossBubble.gui.customComponents.panels.PanelCreateExponential;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction;
import willigrossBubble.gui.customComponents.panels.PanelCreateLinear;
import willigrossBubble.gui.customComponents.panels.PanelIntersection;
import willigrossBubble.gui.customComponents.panels.PanelLoadFunction;
import willigrossBubble.gui.customComponents.panels.PanelMain;
import willigrossBubble.gui.customComponents.panels.PanelNavigation;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FrameMain instance;
	private Container c;
	private CenterPanel panelCenter;
	private PanelNavigation panelSouth;
	
	private MainLogic mainLogic;
	
	
	/**
	 * @return the instance
	 */
	public static FrameMain getInstance() {
		return instance;
	}

	
	/**
	 * @return the panelSouth
	 */
	public PanelNavigation getPanelSouth() {
		return panelSouth;
	}

	
	/**
	 * @return the mainLogic
	 */
	public MainLogic getMainLogic() {
		return mainLogic;
	}

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
		
		
		mainLogic = new MainLogic();
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
		panelSouth.activateButtons(ButtonStates.NONE);
		panelCenter.revalidate();
	}

	public void panelCreateFunction() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelCreateFunction(), BorderLayout.CENTER);
		panelSouth.activateButtons(ButtonStates.MAIN_MENU);
		panelCenter.revalidate();
	}

	public void panelLoadFunction() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelLoadFunction(), BorderLayout.CENTER);
		panelSouth.activateButtons(ButtonStates.MAIN_MENU);
		panelCenter.revalidate();
	}

	public void panelIntersection() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelIntersection(), BorderLayout.CENTER);
		panelSouth.activateButtons(ButtonStates.MAIN_MENU);
		panelCenter.revalidate();
	}
	

	public void createLinear() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelCreateLinear(), BorderLayout.CENTER);
		panelSouth.activateButtons(ButtonStates.BOTH);
		panelCenter.revalidate();
	}

	public void createExponential() {
		c.remove(panelCenter);
		c.add(panelCenter = new PanelCreateExponential(), BorderLayout.CENTER);
		panelSouth.activateButtons(ButtonStates.BOTH);
		panelCenter.revalidate();
	}
	
	

	
}
