package willigross.desktop.gui.panels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.desktop.data.Strings;
import willigross.desktop.gui.FrameMain;
import willigross.desktop.gui.buttons.CustomButtonLarge;

public class PanelCreateFunction_Menu extends RequestFocusForDefaultComponentPanel {

	private static final long		serialVersionUID	= 1L;
	
	private static final Logger		logger				= LoggerFactory.getLogger(PanelCreateFunction_Menu.class);
	
	private final JLabel			heading;
	private final CustomButtonLarge	type, linearT2P, exponentialT2P;

	public PanelCreateFunction_Menu() {

		logger.info("Initializing new PanelCreateFunction_Menu"); //$NON-NLS-1$
		
		setLayout(null);

		heading = new JLabel(Strings.getStringAsHTML("PanelCreateFunction_Menu.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);

		type = new CustomButtonLarge(Strings.getStringAsHTML("PanelCreateFunction_Menu.button_type")); //$NON-NLS-1$
		type.setLocation(100, 110);
		type.addActionListener(e -> {
			final CenterPanel instance = FrameMain.getInstance().getPanelCenter();
			if (instance instanceof PanelCreateFunction)
				((PanelCreateFunction) instance).typeFunction();
		});
		add(type);

		linearT2P = new CustomButtonLarge(Strings.getStringAsHTML("PanelCreateFunction_Menu.button_linearT2P")); //$NON-NLS-1$
		linearT2P.setLocation(100, 145);
		linearT2P.addActionListener(e -> {
			final CenterPanel instance = FrameMain.getInstance().getPanelCenter();
			if (instance instanceof PanelCreateFunction)
				((PanelCreateFunction) instance).createLinear();
		});
		add(linearT2P);

		exponentialT2P = new CustomButtonLarge(
				Strings.getStringAsHTML("PanelCreateFunction_Menu.button_exponentialT2P")); //$NON-NLS-1$
		exponentialT2P.setLocation(100, 180);
		exponentialT2P.addActionListener(e -> {
			final CenterPanel instance = FrameMain.getInstance().getPanelCenter();
			if (instance instanceof PanelCreateFunction)
				((PanelCreateFunction) instance).createExponential();
		});
		add(exponentialT2P);

		setDefaultComponent(type);
	}
}
