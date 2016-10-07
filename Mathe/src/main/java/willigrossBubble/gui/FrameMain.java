package willigrossBubble.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import willigrossBubble.core.Controller;
import willigrossBubble.core.gui.IGUIController;
import willigrossBubble.core.logic.Function;
import willigrossBubble.data.Strings;
import willigrossBubble.gui.customComponents.panels.CenterPanel;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction;
import willigrossBubble.gui.customComponents.panels.PanelFunctionActionsMenu;
import willigrossBubble.gui.customComponents.panels.PanelIntersection;
import willigrossBubble.gui.customComponents.panels.PanelIntersection_FunctionSelection;
import willigrossBubble.gui.customComponents.panels.PanelLoadFunction;
import willigrossBubble.gui.customComponents.panels.PanelMain;
import willigrossBubble.gui.customComponents.panels.PanelNavigation;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class FrameMain extends JFrame implements IGUIController {

	private static final long		serialVersionUID	= 1L;
	
	/** Heading font */
	private static Font				headingFont;
	
	/** Monospaced Font */
	private static Font				monospacedFont;
	
	/** Reference to the main controller */
	private Controller				controller;					//Will be used for Logger
	
	/** Content pane of FrameMain */
	private final Container			c;
	
	/**
	 * Placeholder for subclasses of CenterPanel Will be instantiated with different panels for example menus
	 */
	private CenterPanel				panelCenter;
	
	/** Panel with back and main menu buttons */
	private final PanelNavigation	panelSouth;

	/** Initializes fonts */
	static {
		try {
			headingFont = Font
					.createFont(Font.TRUETYPE_FONT,
							System.class.getResourceAsStream("/assets/fonts/Montserrat-Regular.otf")) //$NON-NLS-1$
					.deriveFont(Font.PLAIN, 15);
			monospacedFont = Font
					.createFont(Font.TRUETYPE_FONT,
							System.class.getResourceAsStream("/assets/fonts/mplus-1m-regular.ttf")) //$NON-NLS-1$
					.deriveFont(Font.PLAIN, 13);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(headingFont);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(monospacedFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	/** Constructor of FrameMain that sets properties and creates navigation and main panel */
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
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * @return the headingFont
	 */
	public static Font getHeadingFont() {
		return headingFont;
	}
	
	/**
	 * @return the monospacedFont
	 */
	public static Font getMonospacedFont() {
		return monospacedFont;
	}
	
	/**
	 * @return the southern panel
	 */
	public PanelNavigation getPanelSouth() {
		return panelSouth;
	}

	/**
	 * @return the central panel
	 */
	public CenterPanel getPanelCenter() {
		return panelCenter;
	}

	/**
	 * Sets the central panel and updates frame
	 *
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

	/** Create PanelMain */
	public void panelMain() {
		setPanelCenter(new PanelMain(), ButtonStates.NONE);
	}

	/** Create PanelCreateFunction */
	public void panelCreateFunction() {
		setPanelCenter(new PanelCreateFunction(), ButtonStates.MAIN_MENU);
	}

	/** Create PanelLoadFunction */
	public void panelLoadFunction() {
		setPanelCenter(new PanelLoadFunction(), ButtonStates.MAIN_MENU);
	}

	/** Create PanelIntersection_FunctionSelection */
	public void panelIntersection_FunctionSelection() {
		setPanelCenter(new PanelIntersection_FunctionSelection(), ButtonStates.MAIN_MENU);
	}

	/** Create PanelIntersection */
	public void panelIntersection(Function function1, Function function2) {
		setPanelCenter(new PanelIntersection(function1, function2), ButtonStates.BOTH);
	}

	/** Create PanelFunctionActionsMenu */
	public void panelFunctionActionsMenu(Function f, CenterPanel caller) {
		setPanelCenter(new PanelFunctionActionsMenu(f, caller), ButtonStates.BOTH);
	}

	/** Handle back command */
	public void back() {
		panelCenter.back();
	}
	
}
