package willigrossBubble.gui.customComponents.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import willigrossBubble.Function;
import willigrossBubble.gui.FrameMain;

public class PanelFunctionActionsMenu extends CenterPanel {
	
	private static final long serialVersionUID = 1L;
	private static PanelFunctionActionsMenu instance;
	private CenterPanel caller;
	private Function function;
	private PanelFunctionActionsMenu_Menu menu;
	private JPanel option;
	
	public PanelFunctionActionsMenu(Function f, CenterPanel caller) {
		this.caller = caller;
		function = f;
		instance = this;
		menu = new PanelFunctionActionsMenu_Menu(function);
		option = new JPanel(); //TODO
		
		setLayout(new GridLayout(2, 1));
		add(menu);
	}
	
	
	/**
	 * @return the instance
	 */
	public static PanelFunctionActionsMenu getInstance() {
		return instance;
	}

	@Override
	public void back() {
		FrameMain.getInstance().setPanelCenter(caller);
	}


	public void table() {
		remove(option);
		add(option = new PanelFunctionActionsMenu_Table());
		option.revalidate();
	}


	public void point() {
		remove(option);
		add(option = new PanelFunctionActionsMenu_Point());
		option.revalidate();
	}


	public void mirror() {
		remove(option);
		add(option = new PanelFunctionActionsMenu_Mirror());
		option.revalidate();
	}	
	
	
	
	
	
}
