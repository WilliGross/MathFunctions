package willigross.desktop.gui.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.desktop.gui.FrameMain;
import willigross.desktop.gui.panels.PanelCreateFunction_ThroughTwoPoints.FunctionType;
import willigross.desktop.gui.panels.PanelNavigation.ButtonStates;

public class PanelCreateFunction extends CenterPanel {

	private static final long				serialVersionUID	= 1L;
	
	private static final Logger				logger				= LoggerFactory.getLogger(PanelCreateFunction.class);

	/** object that holds the subpanel menu */
	private final PanelCreateFunction_Menu	menu;

	/** object that holds the subpanel for creating functions */
	private JPanel							createFunction;

	/** Constructor that creates the subpanel menu and sets some properties */
	public PanelCreateFunction() {

		logger.info(
				"Initializing new PanelCreateFunction - container for a menu and an option panel in the south region"); //$NON-NLS-1$

		menu = new PanelCreateFunction_Menu();

		setLayout(new GridLayout(2, 1));
		add(menu);

		setDefaultComponent(menu);
	}

	/** Creates subpanel for typing functions */
	public void typeFunction() {
		setSubpanelSouth(new PanelCreateFunction_TypeFunction());
	}

	/** Creates subpanel for creating linear functions */
	public void createLinear() {
		setSubpanelSouth(new PanelCreateFunction_ThroughTwoPoints(FunctionType.LINEAR));
	}

	/** Creates subpanel for creating exponential functions */
	public void createExponential() {
		setSubpanelSouth(new PanelCreateFunction_ThroughTwoPoints(FunctionType.EXPONENTIAL));
	}

	/**
	 * Sets the southern panel and rebalidates components
	 *
	 * @param panel the new panel to be set
	 */
	private void setSubpanelSouth(JPanel panel) {
		logger.info("Updating south panel with {}", panel.getClass().getName()); //$NON-NLS-1$
		if (createFunction != null)
			remove(createFunction);
		add(createFunction = panel);
		FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.BOTH);
		createFunction.revalidate();
		createFunction.requestFocusInWindow();
	}

	/** Implementation of back () method from superclass */
	@Override
	public void back() {
		if (createFunction != null) {
			logger.info("'Folding in' south panel"); //$NON-NLS-1$
			remove(createFunction);
			createFunction = null;
			repaint();
			FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.MAIN_MENU);
		} else
			FrameMain.getInstance().panelMain(); // should never be executed since only main menu button is showing when this would be needed
	}

}
