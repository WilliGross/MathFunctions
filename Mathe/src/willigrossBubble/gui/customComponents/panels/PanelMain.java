package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonMedium;

public class PanelMain extends CenterPanel {
	
	private static final long serialVersionUID = 1L;
	CustomButtonMedium b1_create, b2_load, b3_intersection, b4_close; 
	JLabel desc;
	
	public PanelMain() {

		b1_create = new CustomButtonMedium("Create a function");
		b2_load = new CustomButtonMedium("Load a function");
		b3_intersection = new CustomButtonMedium("Intersection of two functions");
		b4_close = new CustomButtonMedium("Close");
		desc = new JLabel("What would you like to do?", SwingConstants.CENTER);
		
		setLayout(null);
		
		b1_create.setLocation(50, 150);
		b1_create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().panelCreateFunction();			
			}
				
		});
		add(b1_create);

		b2_load.setLocation(350, 150);
		b2_load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().panelLoadFunction();
			}
			
		});
		add(b2_load);
		
		b3_intersection.setLocation(50, 200);
		b3_intersection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().panelIntersection();
			}
			
		});
		add(b3_intersection);
		
		b4_close.setLocation(350, 200);
		b4_close.setBackground(Color.RED);
		b4_close.setForeground(Color.BLACK);
		b4_close.setFont(new Font("Calibri", Font.ITALIC, 15));
		b4_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}			
		});
		add(b4_close);
		
		desc.setFont(new Font("Calibri", Font.PLAIN, 15));
		desc.setBounds((600-250)/2, 40, 250, 30);
		add(desc);
	}

	@Override
	public void back() {
		
	}

}
