package willigrossBubble.gui.customComponents.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import willigrossBubble.Function;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class PanelFunctionActionsMenu extends CenterPanel {
	
	private static final long serialVersionUID = 1L;
	private CenterPanel caller;
	private Function function;
	private PanelFunctionActionsMenu_Menu menu;
	private JPanel option;
	
	public PanelFunctionActionsMenu(Function f, CenterPanel caller) {
		this.caller = caller;
		function = f;
		menu = new PanelFunctionActionsMenu_Menu(function);
		
		setLayout(new GridLayout(2, 1));
		add(menu);
		
		setDefaultComponent(menu);
	}
	
	public void table() {
		setSubpanelSouth(new PanelFunctionActionsMenu_Table(function));
	}


	public void point() {
		setSubpanelSouth(new PanelFunctionActionsMenu_Point(function));
	}


	public void mirror() {
		setSubpanelSouth(new PanelFunctionActionsMenu_Mirror(function));
	}	
	
	public void setSubpanelSouth(JPanel panel) {
		if (option != null)
			remove(option);
		add(option = panel);
		option.revalidate();
		option.requestFocusInWindow();
	}

	@Override
	public void back() {
		if (option != null) {
			remove(option);
			option = null;
			repaint();
		} else
			FrameMain.getInstance().setPanelCenter(caller, ButtonStates.BOTH);
	}
	
}
