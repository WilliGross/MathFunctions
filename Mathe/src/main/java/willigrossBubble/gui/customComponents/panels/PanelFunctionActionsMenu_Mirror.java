package willigrossBubble.gui.customComponents.panels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.Function;
import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;

public class PanelFunctionActionsMenu_Mirror extends RequestFocusForDefaultComponentPanel {

	private static final long		serialVersionUID	= 1L;
	private final JLabel			heading, result;
	private final CustomButtonSmall	x, y, origin, go;
	private final Function			functionBase;
	private Function				functionMirrored;
	
	public PanelFunctionActionsMenu_Mirror(Function f) {
		
		functionBase = f;
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);

		result = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
		result.setBounds(100, 90, 400, 30);
		add(result);
		
		go = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_go")); //$NON-NLS-1$
		go.setLocation(250, 130);
		go.setEnabled(false);
		go.addActionListener(e -> {
			FrameMain.getInstance().getMainLogic().storeFunction(functionMirrored);
			FrameMain.getInstance().panelFunctionActionsMenu(functionMirrored,
					FrameMain.getInstance().getPanelCenter());
		});
		add(go);
		
		x = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_x-axis")); //$NON-NLS-1$
		x.setLocation(100, 40);
		x.addActionListener(e -> {
			functionMirrored = functionBase.mirrorX();
			result.setText(functionMirrored.toString());
			go.setEnabled(true);
		});
		add(x);
		
		y = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_y-axis")); //$NON-NLS-1$
		y.setLocation(250, 40);
		y.addActionListener(e -> {
			functionMirrored = functionBase.mirrorY();
			result.setText(functionMirrored.toString());
			go.setEnabled(true);
		});
		add(y);

		origin = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_origin")); //$NON-NLS-1$
		origin.setLocation(400, 40);
		origin.addActionListener(e -> {
			functionMirrored = functionBase.mirrorOrigin();
			result.setText(functionMirrored.toString());
			go.setEnabled(true);
		});
		add(origin);
		
		setDefaultComponent(x);
	}
	
}
