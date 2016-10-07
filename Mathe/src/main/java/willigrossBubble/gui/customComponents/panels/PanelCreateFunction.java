package willigrossBubble.gui.customComponents.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import willigrossBubble.core.Controller;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction_ThroughTwoPoints.FunctionType;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class PanelCreateFunction extends CenterPanel {
	
	private static final long				serialVersionUID	= 1L;
	
	/** object that holds the subpanel menu */
	private final PanelCreateFunction_Menu	menu;
	
	/** object that holds the subpanel for creating functions */
	private JPanel							createFunction;
	
	/** Constructor that creates the subpanel menu and sets some properties */
	public PanelCreateFunction() {
		
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
		if (createFunction != null)
			remove(createFunction);
		add(createFunction = panel);
		((FrameMain) Controller.getInstance().getGUIController()).getPanelSouth().activateButtons(ButtonStates.BOTH);
		createFunction.revalidate();
		createFunction.requestFocusInWindow();
	}
	
	/** Implementation of back () method from superclass */
	@Override
	public void back() {
		if (createFunction != null) {
			remove(createFunction);
			createFunction = null;
			repaint();
			((FrameMain) Controller.getInstance().getGUIController()).getPanelSouth()
					.activateButtons(ButtonStates.MAIN_MENU);
		} else
			((FrameMain) Controller.getInstance().getGUIController()).panelMain(); // should never be executed
		// since only main menu button
		// is showing when this would
		// be needed
	}
	
}
