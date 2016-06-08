package willigrossBubble.gui.customComponents.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction_ThroughTwoPoints.FunctionType;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class PanelCreateFunction extends CenterPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	private static PanelCreateFunction instance;
	private PanelCreateFunction_Menu menu;
	private JPanel createFunction;
	
	public PanelCreateFunction() {
		
		instance = this;
		
		menu = new PanelCreateFunction_Menu();
		createFunction = new PanelCreateFunction_TypeFunction();
		
		setLayout(new GridLayout(2, 1));
		add(menu);

	}
	
	
	/**
	 * @return the instance
	 */
	public static PanelCreateFunction getInstance() {
		return instance;
	}
	
	public void typeFunction() {
		remove(createFunction);
		add(createFunction = new PanelCreateFunction_TypeFunction(), BorderLayout.CENTER);
		FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.BOTH);
		createFunction.revalidate();
	}
	
	public void createLinear() {
		remove(createFunction);
		add(createFunction = new PanelCreateFunction_ThroughTwoPoints(FunctionType.LINEAR), BorderLayout.CENTER);
		FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.BOTH);
		createFunction.revalidate();
	}

	public void createExponential() {
		remove(createFunction);
		add(createFunction = new PanelCreateFunction_ThroughTwoPoints(FunctionType.EXPONENTIAL), BorderLayout.CENTER);
		FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.BOTH);
		createFunction.revalidate();
	}


	@Override
	public void back() {
		if (createFunction.isVisible()) {
			createFunction.setVisible(false);
			FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.MAIN_MENU);
		} else
			FrameMain.getInstance().panelMain();
	}

}
