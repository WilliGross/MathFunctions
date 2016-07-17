package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButton;

public class PanelNavigation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CustomButton mainMenu, back;
	
	public PanelNavigation() {
		
		setLayout(null);
		setPreferredSize(new Dimension(600, 50));
		Color c = new Color(0, 111, 174);
		
		mainMenu = new CustomButton(Strings.getString("PanelNavigation.button_mainMenu")); //$NON-NLS-1$
		mainMenu.setBackground(c);
		mainMenu.setForeground(Color.WHITE);
		mainMenu.setBounds(10, 10, 145, 30);
		mainMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().panelMain();
			}
		});
		add(mainMenu);
		
		back = new CustomButton(Strings.getString("PanelNavigation.button_back")); //$NON-NLS-1$
		back.setBackground(c);
		back.setForeground(Color.WHITE);
		back.setBounds(430, 10, 145, 30);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().back();
			}
		});
		add(back);
	}
	
	public void setButtonVisibility(boolean mainMenu, boolean back) {
		if (mainMenu)
			this.mainMenu.setEnabled(true);
		else
			this.mainMenu.setEnabled(false);
		if (back)
			this.back.setEnabled(true);
		else
			this.back.setEnabled(false);
	}
	
	public void activateButtons(ButtonStates state) {
		switch (state) {
			case BOTH:
				this.mainMenu.setEnabled(true);
				this.back.setEnabled(true);
				break;
			case NONE:
				this.mainMenu.setEnabled(false);
				this.back.setEnabled(false);
				break;
			case MAIN_MENU:
				this.mainMenu.setEnabled(true);
				this.back.setEnabled(false);
				break;
			case BACK:
				this.mainMenu.setEnabled(false);
				this.back.setEnabled(true);
				break;
			default:
				break;
		}
	}
	public enum ButtonStates {
		BOTH, NONE, MAIN_MENU, BACK;
	}
}
