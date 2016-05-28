package willigrossBubble.gui.customComponents.panels;

import willigrossBubble.gui.FrameMain;

public class PanelTypeFunction extends CenterPanel {

	private static final long serialVersionUID = 1L;
	
	public PanelTypeFunction() {
		
	}

	@Override
	public void back() {
		FrameMain.instance.panelCreateFunction();
	}

}
