package willigrossBubble.gui.customComponents.panels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.core.Controller;
import willigrossBubble.data.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonLarge;

public class PanelCreateFunction_Menu extends RequestFocusForDefaultComponentPanel {
	
	private static final long		serialVersionUID	= 1L;
	private final JLabel			heading;
	private final CustomButtonLarge	b1_type, b2_linearT2P, b3_exponentialT2P;
	
	public PanelCreateFunction_Menu() {
		
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelCreateFunction_Menu.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);
		
		b1_type = new CustomButtonLarge(Strings.getStringAsHTML("PanelCreateFunction_Menu.button_type")); //$NON-NLS-1$
		b1_type.setLocation(100, 110);
		b1_type.addActionListener(e -> {
			final CenterPanel instance = ((FrameMain) Controller.getInstance().getGUIController()).getPanelCenter();
			if (instance instanceof PanelCreateFunction)
				((PanelCreateFunction) instance).typeFunction();
		});
		add(b1_type);
		
		b2_linearT2P = new CustomButtonLarge(Strings.getStringAsHTML("PanelCreateFunction_Menu.button_linearT2P")); //$NON-NLS-1$
		b2_linearT2P.setLocation(100, 145);
		b2_linearT2P.addActionListener(e -> {
			final CenterPanel instance = ((FrameMain) Controller.getInstance().getGUIController()).getPanelCenter();
			if (instance instanceof PanelCreateFunction)
				((PanelCreateFunction) instance).createLinear();
		});
		add(b2_linearT2P);
		
		b3_exponentialT2P = new CustomButtonLarge(
				Strings.getStringAsHTML("PanelCreateFunction_Menu.button_exponentialT2P")); //$NON-NLS-1$
		b3_exponentialT2P.setLocation(100, 180);
		b3_exponentialT2P.addActionListener(e -> {
			final CenterPanel instance = ((FrameMain) Controller.getInstance().getGUIController()).getPanelCenter();
			if (instance instanceof PanelCreateFunction)
				((PanelCreateFunction) instance).createExponential();
		});
		add(b3_exponentialT2P);
		
		setDefaultComponent(b1_type);
	}
}
