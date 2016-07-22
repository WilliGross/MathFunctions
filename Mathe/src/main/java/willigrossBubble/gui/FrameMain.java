package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import willigrossBubble.Function;
import willigrossBubble.MainLogic;
import willigrossBubble.Strings;
import willigrossBubble.gui.customComponents.panels.CenterPanel;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction;
import willigrossBubble.gui.customComponents.panels.PanelFunctionActionsMenu;
import willigrossBubble.gui.customComponents.panels.PanelIntersection;
import willigrossBubble.gui.customComponents.panels.PanelIntersection_FunctionSelection;
import willigrossBubble.gui.customComponents.panels.PanelLoadFunction;
import willigrossBubble.gui.customComponents.panels.PanelMain;
import willigrossBubble.gui.customComponents.panels.PanelNavigation;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**Global font*/
	private static Font globalFont = new Font(Strings.getStringAsHTML("FrameMain.globalFont"), Font.PLAIN, 15); //$NON-NLS-1$
	
	/**Global font bold*/
	private static Font globalFont_Bold = new Font(Strings.getStringAsHTML("FrameMain.globalFont_Bold"), Font.BOLD, 15); //$NON-NLS-1$
	
	/**Instance of FrameMain that is set on program start*/
	private static FrameMain instance; //TODO Can be final?
	
	/**Content pane of FrameMain*/
	private Container c;
	
	/**
	 * Placeholder for subclasses of CenterPanel
	 * Will be instantiated with different panels for example memus
	 */
	private CenterPanel panelCenter;
	
	/**Panel with back and main menu buttons*/
	private PanelNavigation panelSouth;
	
	/**Main logic controller object*/
	private MainLogic mainLogic;
	
	
	/**Constructor of FrameMain that sets properties and creates navigation and main panel*/
	public FrameMain() {
		
		setTitle(Strings.getString("FrameMain.title")); //$NON-NLS-1$
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


	/**Start program*/
	public static void main(String[] args) {
		instance = new FrameMain();
	}


	/**
	 * @return the global font
	 */
	public static Font getGlobalFont() {
		return globalFont;
	}

	/**
	 * @return the global font in font style bold
	 */
	public static Font getGlobalFont_Bold() {
		return globalFont_Bold;
	}


	/**
	 * @return the instance of FrameMain
	 */
	public static FrameMain getInstance() {
		return instance;
	}

	
	/**
	 * @return the southern panel
	 */
	public PanelNavigation getPanelSouth() {
		return panelSouth;
	}

	
	/**
	 * @return the main logic controller
	 */
	public MainLogic getMainLogic() {
		return mainLogic;
	}

	
	/**
	 * @return the central panel
	 */
	public CenterPanel getPanelCenter() {
		return panelCenter;
	}


	/**
	 * Sets the central panel and updates frame
	 * @param panel the new panel to be set
	 * @param buttonState the mode for the navigation panel (both, none, back, main menu)
	 */
	public void setPanelCenter(CenterPanel panel, ButtonStates buttonState) {
		c.remove(panelCenter);
		c.add(panelCenter = panel, BorderLayout.CENTER);
		panelSouth.activateButtons(buttonState);
		panelCenter.revalidate();
		panelCenter.repaint();
		panelCenter.requestFocusInWindow();
	}


	/**Create PanelMain*/
	public void panelMain() {
		setPanelCenter(new PanelMain(), ButtonStates.NONE);
	}

	
	/**Create PanelCreateFunction */
	public void panelCreateFunction() {
		setPanelCenter(new PanelCreateFunction(), ButtonStates.MAIN_MENU);
	}


	/**Create PanelLoadFunction */
	public void panelLoadFunction() {
		setPanelCenter(new PanelLoadFunction(), ButtonStates.MAIN_MENU);
	}


	/**Create PanelIntersection_FunctionSelection */
	public void panelIntersection_FunctionSelection() {
		setPanelCenter(new PanelIntersection_FunctionSelection(), ButtonStates.MAIN_MENU);
	}
	
	
	/**Create PanelIntersection*/
	public void panelIntersection(Function function1, Function function2) {
		setPanelCenter(new PanelIntersection(function1, function2), ButtonStates.BOTH);
	}
	
	
	/**Create PanelFunctionActionsMenu */
	public void panelFunctionActionsMenu(Function f, CenterPanel caller) {
		setPanelCenter(new PanelFunctionActionsMenu(f, caller), ButtonStates.BOTH);
	}


	/**Handle back command*/
	public void back() {
		panelCenter.back();
	}
	
}
