package willigrossBubble.gui.customComponents.buttons;

/** For "Go" buttons */
public class CustomButtonSmall extends CustomButton {

	private static final long serialVersionUID = 1L;
	
	public CustomButtonSmall(String text) {
		super(text);
		setSize(100, 30);
	}
	
	public CustomButtonSmall() {
		this("");
	}
}
