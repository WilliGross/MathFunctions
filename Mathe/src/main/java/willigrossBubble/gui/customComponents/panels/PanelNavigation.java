package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigrossBubble.data.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButton;

public class PanelNavigation extends JPanel {

	private static final long	serialVersionUID	= 1L;
	
	private static final Logger	logger				= LoggerFactory.getLogger(PanelMain.class);
	
	private final CustomButton	mainMenu, back;

	public PanelNavigation() {

		logger.info("Initializing new PanelNavigation"); //$NON-NLS-1$

		setLayout(null);
		setPreferredSize(new Dimension(600, 50));
		final Color c = new Color(0, 111, 174);

		mainMenu = new CustomButton(Strings.getStringAsHTML("PanelNavigation.button_mainMenu")); //$NON-NLS-1$
		mainMenu.setBackground(c);
		mainMenu.setForeground(Color.WHITE);
		mainMenu.setBounds(10, 10, 145, 30);
		mainMenu.addActionListener(e -> FrameMain.getInstance().panelMain());
		add(mainMenu);

		back = new CustomButton(Strings.getStringAsHTML("PanelNavigation.button_back")); //$NON-NLS-1$
		back.setBackground(c);
		back.setForeground(Color.WHITE);
		back.setBounds(430, 10, 145, 30);
		back.addActionListener(e -> FrameMain.getInstance().back());
		add(back);
	}

	public void activateButtons(ButtonStates state) {
		logger.info("Updating button visibility: {}", state); //$NON-NLS-1$
		switch (state) {
			case BOTH:
				mainMenu.setEnabled(true);
				back.setEnabled(true);
				break;
			case NONE:
				mainMenu.setEnabled(false);
				back.setEnabled(false);
				break;
			case MAIN_MENU:
				mainMenu.setEnabled(true);
				back.setEnabled(false);
				break;
			case BACK:
				mainMenu.setEnabled(false);
				back.setEnabled(true);
				break;
			default:
				break;
		}
	}
	
	public enum ButtonStates {
		BOTH, NONE, MAIN_MENU, BACK;
	}
}
