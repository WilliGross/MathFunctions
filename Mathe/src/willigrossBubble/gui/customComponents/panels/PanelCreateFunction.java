package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonLarge;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;
import willigrossBubble.gui.customComponents.panels.PanelNavigation.ButtonStates;

public class PanelCreateFunction extends CenterPanel {

	private static final long serialVersionUID = 1L;

	private JLabel desc;
	private CustomButtonLarge b1_type, b2_linearT2P, b3_exponentialT2P;
	
	//for type function
	private JLabel f;
	private JTextField function;
	private CustomButtonSmall go;
	
	
	public PanelCreateFunction() {
		setLayout(null);
		
		desc = new JLabel("How would you like to create your function?");
		desc.setBounds(175, 40, 250, 30);
		add(desc);
		
		b1_type = new CustomButtonLarge("Type your function");
		b1_type.setLocation(100, 100);
		b1_type.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.BOTH);
				setTypeFunctionVisible(true);
				function.requestFocusInWindow();
				
			}
			
		});
		add(b1_type);
		
		b2_linearT2P = new CustomButtonLarge("Linear function through 2 points");
		b2_linearT2P.setLocation(100, 135);
		b2_linearT2P.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().createLinear();
			}
			
		});
		add(b2_linearT2P);
		
		b3_exponentialT2P = new CustomButtonLarge("Exponential function through 2 points");
		b3_exponentialT2P.setLocation(100, 170);
		b3_exponentialT2P.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().createExponential();
			}
		});
		
		add(b3_exponentialT2P);
		
		
		//for type function
		f = new JLabel("f(x) = ", SwingConstants.CENTER);
		f.setBounds(25, 240, 50, 30);
		f.setVisible(false);
		add(f);
		
		function = new JTextField();
		function.setBounds(75, 240, 475, 30);
		function.setVisible(false);
		function.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					go.doClick();
			}
			
		});
		add(function);
		
		go = new CustomButtonSmall("Go");
		go.setBounds(250, 280, 100, 30);
		go.setVisible(false);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		add(go);
	}


	@Override
	public void back() {
		if (f.isVisible()) {
			FrameMain.getInstance().getPanelSouth().activateButtons(ButtonStates.NONE);
			setTypeFunctionVisible(false);
			function.setText("");
		} else
			FrameMain.getInstance().panelMain();
	}
	
	public void setTypeFunctionVisible(boolean visible) {
		f.setVisible(visible);
		function.setVisible(visible);
		go.setVisible(visible);
	}
	
}
