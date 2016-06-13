package willigrossBubble.gui.customComponents.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import willigrossBubble.Function;
import willigrossBubble.gui.FrameMain;

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
		option = new JPanel(); //TODO
		
		setLayout(new GridLayout(2, 1));
		add(menu);
	}
	
	@Override
	public void back() {
		FrameMain.getInstance().setPanelCenter(caller);
	}


	public void table() {
		remove(option);
		add(option = new PanelFunctionActionsMenu_Table(function));
		option.revalidate();
	}


	public void point() {
		remove(option);
		add(option = new PanelFunctionActionsMenu_Point(function));
		option.revalidate();
	}


	public void mirror() {
		remove(option);
		add(option = new PanelFunctionActionsMenu_Mirror(function));
		option.revalidate();
	}	
	
	
	
	
	
}
