package willigross.desktop.gui.buttons;

/** For buttons in selections */
public class CustomButtonLarge extends CustomButton {

	private static final long serialVersionUID = 1L;

	/** Implementation of super constructor with button size 400 x 30 (large) */
	public CustomButtonLarge(String text) {
		super(text);
		setSize(400, 30);
	}

	/** Implementation of super constructor with button size 400 x 30 (large) */
	public CustomButtonLarge() {
		this(""); //$NON-NLS-1$
	}
}
