package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import willigrossBubble.Function;
import willigrossBubble.MainLogic;
import willigrossBubble.gui.customComponents.panels.CenterPanel;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction;
import willigrossBubble.gui.customComponents.panels.PanelFunctionActionsMenu;
import willigrossBubble.gui.customComponents.panels.PanelIntersection;
import willigrossBubble.gui.customComponents.panels.PanelLoadFunction;
import willigrossBubble.gui.customComponents.panels.PanelMain;
import willigrossBubble.gui.customComponents.panels.PanelNavigation;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Font globalFont = new Font("Dialog", Font.PLAIN, 15);
	private static Font globalFont_Bold = new Font("Dialog", Font.BOLD, 15);
	private static FrameMain instance;
	private Container c;
	private CenterPanel panelCenter;
	private PanelNavigation panelSouth;
	
	private MainLogic mainLogic;
	
	
	public FrameMain() {
		
		setTitle("Mathe");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
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


	/**
	 * @return the font
	 */
	public static Font getGlobalFont() {
		return globalFont;
	}


	public static Font getGlobalFont_Bold() {
		return globalFont_Bold;
	}


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

	
	/**
	 * @return the panelCenter
	 */
	public CenterPanel getPanelCenter() {
		return panelCenter;
	}


	public void setPanelCenter(CenterPanel panel, ButtonStates buttonState) {
		c.remove(panelCenter);
		c.add(panelCenter = panel, BorderLayout.CENTER);
		panelSouth.activateButtons(buttonState);
		panelCenter.revalidate();
		panelCenter.repaint();
		panelCenter.requestFocusInWindow();
	}


	public void panelMain() {
		setPanelCenter(new PanelMain(), ButtonStates.NONE);
	}

	public void panelCreateFunction() {
		setPanelCenter(new PanelCreateFunction(), ButtonStates.MAIN_MENU);
	}

	public void panelLoadFunction() {
		setPanelCenter(new PanelLoadFunction(), ButtonStates.MAIN_MENU);
	}

	public void panelIntersection() {
		setPanelCenter(new PanelIntersection(), ButtonStates.MAIN_MENU);
	}
	
	public void panelFunctionActionsMenu(Function f, CenterPanel caller) {
		setPanelCenter(new PanelFunctionActionsMenu(f, caller), ButtonStates.BOTH);
	}


	public void back() {
		panelCenter.back();
	}
	
}
