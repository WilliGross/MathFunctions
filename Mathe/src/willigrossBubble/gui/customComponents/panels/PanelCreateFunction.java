package willigrossBubble.gui.customComponents.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.panels.PanelCreateFunction_ThroughTwoPoints.FunctionType;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class PanelCreateFunction extends CenterPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	private PanelCreateFunction_Menu menu;
	private JPanel createFunction;
	
	public PanelCreateFunction() {
		
		menu = new PanelCreateFunction_Menu();
		createFunction = new PanelCreateFunction_TypeFunction();
		
		setLayout(new GridLayout(2, 1));
		add(menu);
		
		setDefaultComponent(menu);
	}

	
	public void typeFunction() {
		setSubpanelSouth(new PanelCreateFunction_TypeFunction());
	}
	
	public void createLinear() {
		setSubpanelSouth(new PanelCreateFunction_ThroughTwoPoints(FunctionType.LINEAR));
	}

	public void createExponential() {
		setSubpanelSouth(new PanelCreateFunction_ThroughTwoPoints(FunctionType.EXPONENTIAL));
	}
	
	private void setSubpanelSouth(JPanel panel) {
		remove(createFunction);
		add(createFunction = panel);
		FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.BOTH);
		createFunction.revalidate();
		createFunction.requestFocusInWindow();
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
