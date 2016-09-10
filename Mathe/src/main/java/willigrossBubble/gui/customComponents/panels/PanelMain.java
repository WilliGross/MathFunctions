package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonMedium;

public class PanelMain extends CenterPanel {
	
	private static final long			serialVersionUID	= 1L;
	private final CustomButtonMedium	b1_create, b2_load, b3_intersection, b4_close;
	private final JLabel				heading;

	public PanelMain() {
		
		setLayout(null);

		heading = new JLabel(Strings.getStringAsHTML("PanelMain.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds((600 - 250) / 2, 40, 250, 30);
		add(heading);

		b1_create = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_create")); //$NON-NLS-1$
		b1_create.setLocation(50, 150);
		b1_create.addActionListener(e -> FrameMain.getInstance().panelCreateFunction());
		add(b1_create);
		
		b2_load = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_load")); //$NON-NLS-1$
		b2_load.setLocation(350, 150);
		b2_load.addActionListener(e -> FrameMain.getInstance().panelLoadFunction());
		add(b2_load);

		b3_intersection = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_intersection")); //$NON-NLS-1$
		b3_intersection.setLocation(50, 200);
		b3_intersection.addActionListener(e -> FrameMain.getInstance().panelIntersection_FunctionSelection());
		add(b3_intersection);
		
		b4_close = new CustomButtonMedium(Strings.getStringAsHTML("PanelMain.button_close")); //$NON-NLS-1$
		b4_close.setLocation(350, 200);
		b4_close.setBackground(Color.RED);
		b4_close.setForeground(Color.BLACK);
		b4_close.setFont(new Font(Strings.getStringAsHTML("PanelMain.button_close_font"), Font.ITALIC, 15)); //$NON-NLS-1$
		b4_close.addActionListener(e -> System.exit(0));
		add(b4_close);
		
		setDefaultComponent(b1_create);
	}

	@Override
	public void back() {
		
	}

}
