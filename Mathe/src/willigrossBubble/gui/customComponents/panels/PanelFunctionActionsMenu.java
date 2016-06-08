package willigrossBubble.gui.customComponents.panels;

import willigrossBubble.Function;
import willigrossBubble.gui.FrameMain;

public class PanelFunctionActionsMenu extends CenterPanel {
	
	private static final long serialVersionUID = 1L;
	private CenterPanel caller;
	private Function function;

	public PanelFunctionActionsMenu(Function f, CenterPanel caller) {
	this.caller = caller;
	function = f;
}

	@Override
	public void back() {
		FrameMain.getInstance().setPanelCenter(caller);
	}	
	

	

	
}
