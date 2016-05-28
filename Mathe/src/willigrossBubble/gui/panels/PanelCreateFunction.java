package willigrossBubble.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import willigrossBubble.gui.CustomButton;
import willigrossBubble.gui.FrameMain;

public class PanelCreateFunction extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel desc;
	private CustomButton b1_type, b2_linearT2P, b3_exponentialT2P;
	
	
	public PanelCreateFunction() {
		setLayout(null);
		
		desc = new JLabel("How would you like to create your function?");
		desc.setBounds(175, 40, 250, 30);
		add(desc);
		
		b1_type = new CustomButton("Type your function");
		b1_type.setLocation(100, 100);
		b1_type.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.instance.typeFunction();
			}
			
		});
		add(b1_type);
		
		b2_linearT2P = new CustomButton("Linear function through 2 points");
		b2_linearT2P.setLocation(100, 135);
		b2_linearT2P.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.instance.createLinear();
			}
			
		});
		add(b2_linearT2P);
		
		b3_exponentialT2P = new CustomButton("Exponential function through 2 points");
		b3_exponentialT2P.setLocation(100, 170);
		b3_exponentialT2P.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.instance.createExponential();
			}
		});
		
		add(b3_exponentialT2P);
		
	}
	
}