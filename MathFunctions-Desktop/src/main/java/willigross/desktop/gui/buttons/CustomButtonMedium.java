package willigross.desktop.gui.buttons;

/** For buttons in main menu */
public class CustomButtonMedium extends CustomButton {

	private static final long serialVersionUID = 1L;

	/**
	 * Implementation of super constructor with button size 200 x 30 (medium)
	 */
	public CustomButtonMedium(String text) {
		super(text);
		setSize(200, 30);
	}

	/**
	 * Implementation of super constructor with button size 200 x 30 (medium)
	 */
	public CustomButtonMedium() {
		this(""); //$NON-NLS-1$
	}
}
