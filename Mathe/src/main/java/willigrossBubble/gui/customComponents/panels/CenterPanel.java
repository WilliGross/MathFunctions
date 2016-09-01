package willigrossBubble.gui.customComponents.panels;

public abstract class CenterPanel extends RequestFocusForDefaultComponentPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * guarantee that all panels that are subclasses of CenterPanel have the method back()
	 */
	public abstract void back();
}
