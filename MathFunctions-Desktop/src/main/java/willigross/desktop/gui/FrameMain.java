package willigross.desktop.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.Controller;
import willigross.core.gui.IGUIController;
import willigross.core.logic.Function;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.panels.CenterPanel;
import willigross.desktop.gui.panels.PanelCreateFunction;
import willigross.desktop.gui.panels.PanelFunctionActionsMenu;
import willigross.desktop.gui.panels.PanelIntersection;
import willigross.desktop.gui.panels.PanelIntersection_FunctionSelection;
import willigross.desktop.gui.panels.PanelLoadFunction;
import willigross.desktop.gui.panels.PanelMain;
import willigross.desktop.gui.panels.PanelNavigation;
import willigross.desktop.gui.panels.PanelNavigation.ButtonStates;

public class FrameMain extends JFrame implements IGUIController {

	private static final long		serialVersionUID	= 1L;
	
	private static final Logger		logger				= LoggerFactory.getLogger(FrameMain.class);
	
	/** Heading font */
	private static Font				headingFont;
	
	/** Monospaced Font */
	private static Font				monospacedFont;
	
	private static FrameMain		instance;
	
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
							Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("willigross/desktop/assets/fonts/Montserrat-Regular.otf")) //$NON-NLS-1$
					.deriveFont(Font.PLAIN, 15);
			monospacedFont = Font
					.createFont(Font.TRUETYPE_FONT,
							Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("willigross/desktop/assets/fonts/mplus-1m-regular.ttf")) //$NON-NLS-1$
					.deriveFont(Font.PLAIN, 13);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(headingFont);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(monospacedFont);

			logger.info("Custom fonts registered"); //$NON-NLS-1$
		} catch (IOException | FontFormatException e) {
			logger.error("Caught {} when creating new fonts: ", e.getClass().getName(), e); //$NON-NLS-1$
		}
	}

	/** Constructor of FrameMain that sets properties and creates navigation and main panel */
	public FrameMain() {
		
		logger.info("Creating GUI..."); //$NON-NLS-1$

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setTitle(Strings.getString("FrameMain.title")); //$NON-NLS-1$
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		logger.info("Properties set"); //$NON-NLS-1$

		c = getContentPane();

		panelSouth = new PanelNavigation();
		c.add(panelSouth, BorderLayout.SOUTH);
		panelCenter = new PanelMain();
		panelMain();
		logger.info("Default panels set"); //$NON-NLS-1$
	}

	/**
	 * Returns the instance of FrameMain that is used in the controller
	 *
	 * @return the instance
	 */
	public static FrameMain getInstance() {
		return instance;
	}

	@Override
	public void start(Controller controller) {
		final IGUIController guiController = controller.getGUIController();
		if (guiController instanceof FrameMain)
			instance = (FrameMain) guiController;
		
		logger.info("Setting GUI visible"); //$NON-NLS-1$
		setVisible(true);
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
		logger.info("Central panel updated with {}", panel.getClass().getName()); //$NON-NLS-1$
	}

	public void displayCorruptedFileWarning() {
		JOptionPane.showMessageDialog(this, Strings.getStringAsHTML("FrameMain.functionStorageFileCorrupted_message"), //$NON-NLS-1$
				Strings.getString("FrameMain.functionStorageFileCorrupted_title"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$

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
