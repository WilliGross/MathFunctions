package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonLarge;


public class PanelCreateFunction_Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel desc;
	private CustomButtonLarge b1_type, b2_linearT2P, b3_exponentialT2P;
	
	public PanelCreateFunction_Menu() {
		setLayout(null);
		
		desc = new JLabel("How would you like to create your function?", SwingConstants.CENTER);
		desc.setFont(FrameMain.getGlobalFont());
		desc.setBounds(100, 40, 400, 30);
		add(desc);
		
		b1_type = new CustomButtonLarge("Type your function");
		b1_type.setLocation(100, 110);
		b1_type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelCreateFunction.getInstance().typeFunction();
			}
			
		});
		add(b1_type);
		
		b2_linearT2P = new CustomButtonLarge("Linear function through 2 points");
		b2_linearT2P.setLocation(100, 145);
		b2_linearT2P.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelCreateFunction.getInstance().createLinear();
			}
			
		});
		add(b2_linearT2P);
		
		b3_exponentialT2P = new CustomButtonLarge("Exponential function through 2 points");
		b3_exponentialT2P.setLocation(100, 180);
		b3_exponentialT2P.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelCreateFunction.getInstance().createExponential();
			}
		});
		add(b3_exponentialT2P);
		
	}
}
