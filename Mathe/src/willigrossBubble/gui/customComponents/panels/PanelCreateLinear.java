package willigrossBubble.gui.customComponents.panels;

import willigrossBubble.gui.FrameMain;


public class PanelCreateLinear extends CenterPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void back() {
		FrameMain.instance.panelCreateFunction();
	}
	
}
