package willigross.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.data.Strings;
import willigross.gui.FrameMain;
import willigross.gui.customComponents.buttons.CustomButtonMedium;

public class PanelMain extends CenterPanel {
	
	private static final long			serialVersionUID	= 1L;

	private static final Logger			logger				= LoggerFactory.getLogger(PanelMain.class);

	private final CustomButtonMedium	create, load, intersection, close;
	private final JLabel				heading;

	public PanelMain() {
		
		logger.info("Initializing new PanelMain"); //$NON-NLS-1$
		
		setLayout(null);

		heading = new JLabel(Strings.getStringAsHTML("PanelMain.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds((600 - 250) / 2, 40, 250, 50);
		add(heading);

		create = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_create")); //$NON-NLS-1$
		//		create.setFont(FrameMain.getTextFont());
		create.setLocation(50, 150);
		create.addActionListener(e -> FrameMain.getInstance().panelCreateFunction());
		add(create);
		
		load = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_load")); //$NON-NLS-1$
		load.setLocation(350, 150);
		load.addActionListener(e -> FrameMain.getInstance().panelLoadFunction());
		add(load);

		intersection = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_intersection")); //$NON-NLS-1$
		intersection.setLocation(50, 200);
		intersection.addActionListener(e -> FrameMain.getInstance().panelIntersection_FunctionSelection());
		add(intersection);
		
		close = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_close")); //$NON-NLS-1$
		close.setLocation(350, 200);
		close.setBackground(Color.RED);
		close.setForeground(Color.BLACK);
		close.setFont(new Font(Strings.getStringAsHTML("PanelMain.button_close_font"), Font.ITALIC, 15)); //$NON-NLS-1$
		close.addActionListener(e -> {
			logger.info("Exiting application"); //$NON-NLS-1$
			System.exit(0);
		});
		add(close);
		
		setDefaultComponent(create);
	}

	@Override
	public void back() {
		
	}

}
