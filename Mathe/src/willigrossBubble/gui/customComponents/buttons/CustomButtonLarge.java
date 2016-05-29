package willigrossBubble.gui.customComponents.buttons;

/** For buttons in selections */
public class CustomButtonLarge extends CustomButton {

	private static final long serialVersionUID = 1L;
	
	public CustomButtonLarge(String text) {
		super(text);
		setSize(400, 30);
	}
	
	public CustomButtonLarge() {
		this("");
	}
}
