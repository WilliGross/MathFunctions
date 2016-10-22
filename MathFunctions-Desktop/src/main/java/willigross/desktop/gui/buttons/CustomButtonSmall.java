package willigross.desktop.gui.buttons;

/** For "Go" buttons */
public class CustomButtonSmall extends CustomButton {

	private static final long serialVersionUID = 1L;

	/** Implementation of super constructor with button size 100 x 30 (small) */
	public CustomButtonSmall(String text) {
		super(text);
		setSize(100, 30);
	}

	/** Implementation of super constructor with button size 100 x 30 (small) */
	public CustomButtonSmall() {
		this(""); //$NON-NLS-1$
	}
}
