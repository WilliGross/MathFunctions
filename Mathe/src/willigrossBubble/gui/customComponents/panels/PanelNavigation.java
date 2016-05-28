package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButton;

public class PanelNavigation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CustomButton mainMenu, back;
	
	public PanelNavigation() {
		
		setLayout(null);
		setPreferredSize(new Dimension(600, 50));
		Color c = new Color(0, 111, 174);
		
		mainMenu = new CustomButton("Back to Main Menu");
		mainMenu.setBackground(c);
		mainMenu.setForeground(Color.WHITE);
		mainMenu.setBounds(10, 10, 145, 30);
		mainMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.instance.panelMain();
			}
		});
		add(mainMenu);
		
		back = new CustomButton("Back");
		back.setBackground(c);
		back.setForeground(Color.WHITE);
		back.setBounds(430, 10, 145, 30);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.instance.back();
			}
		});
		add(back);
	}
	
	public void setVisibility(boolean mainMenu, boolean back) {
		if (mainMenu)
			this.mainMenu.setEnabled(true);
		else
			this.mainMenu.setEnabled(false);
		if (back)
			this.back.setEnabled(true);
		else
			this.back.setEnabled(false);
	}
}
