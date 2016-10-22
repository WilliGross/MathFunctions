package willigross.desktop.gui.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.logic.Function;
import willigross.desktop.gui.FrameMain;
import willigross.desktop.gui.panels.PanelNavigation.ButtonStates;

public class PanelFunctionActionsMenu extends CenterPanel {

	private static final long					serialVersionUID	= 1L;
	
	private static final Logger					logger				= LoggerFactory
			.getLogger(PanelFunctionActionsMenu.class);
	
	private final CenterPanel					caller;
	private final Function						function;
	private final PanelFunctionActionsMenu_Menu	menu;
	private JPanel								option;

	public PanelFunctionActionsMenu(Function f, CenterPanel caller) {

		logger.info(
				"Initializing new PanelFunctionActionsMenu - container for a menu and an option panel in the south region"); //$NON-NLS-1$
		
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
		logger.info("Updating south panel with {}", panel.getClass().getName()); //$NON-NLS-1$
		if (option != null)
			remove(option);
		add(option = panel);
		option.revalidate();
		option.requestFocusInWindow();
	}
	
	@Override
	public void back() {
		if (option != null) {
			logger.info("'Folding in' south panel"); //$NON-NLS-1$
			remove(option);
			option = null;
			repaint();
		} else
			FrameMain.getInstance().setPanelCenter(caller, ButtonStates.BOTH);
	}

}
