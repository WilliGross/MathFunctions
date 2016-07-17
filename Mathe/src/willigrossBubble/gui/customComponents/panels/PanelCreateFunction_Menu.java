package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonLarge;


public class PanelCreateFunction_Menu extends RequestFocusForDefaultComponentPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel heading;
	private CustomButtonLarge b1_type, b2_linearT2P, b3_exponentialT2P;
	
	public PanelCreateFunction_Menu() {
		
		setLayout(null);
		
		heading = new JLabel(Strings.getString("PanelCreateFunction_Menu.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 40, 400, 30);
		add(heading);
		
		b1_type = new CustomButtonLarge(Strings.getString("PanelCreateFunction_Menu.button_type")); //$NON-NLS-1$
		b1_type.setLocation(100, 110);
		b1_type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel instance = FrameMain.getInstance().getPanelCenter();
				if (instance instanceof PanelCreateFunction)
					((PanelCreateFunction) instance).typeFunction();
			}
			
		});
		add(b1_type);
		
		b2_linearT2P = new CustomButtonLarge(Strings.getString("PanelCreateFunction_Menu.button_linearT2P")); //$NON-NLS-1$
		b2_linearT2P.setLocation(100, 145);
		b2_linearT2P.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel instance = FrameMain.getInstance().getPanelCenter();
				if (instance instanceof PanelCreateFunction)
					((PanelCreateFunction) instance).createLinear();
			}
			
		});
		add(b2_linearT2P);
		
		b3_exponentialT2P = new CustomButtonLarge(Strings.getString("PanelCreateFunction_Menu.button_exponentialT2P")); //$NON-NLS-1$
		b3_exponentialT2P.setLocation(100, 180);
		b3_exponentialT2P.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel instance = FrameMain.getInstance().getPanelCenter();
				if (instance instanceof PanelCreateFunction)
					((PanelCreateFunction) instance).createExponential();
			}
		});
		add(b3_exponentialT2P);
		
		setDefaultComponent(b1_type);
	}
}
