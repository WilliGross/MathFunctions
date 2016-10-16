package willigrossBubble.core.gui;

import willigrossBubble.core.Controller;

public interface IGUIController {

	/**
	 * This method will be called once the GUIController is registered in the Controller. Here the gui is supposed to be
	 * set visible.
	 */
	public void start(Controller controller);
	
}
