package willigross.desktop.gui.panels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.Controller;
import willigross.core.logic.Function;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.FrameMain;
import willigross.desktop.gui.buttons.CustomButtonSmall;

public class PanelFunctionActionsMenu_Mirror extends RequestFocusForDefaultComponentPanel {

	private static final long		serialVersionUID	= 1L;

	private static final Logger		logger				= LoggerFactory
			.getLogger(PanelFunctionActionsMenu_Mirror.class);
	
	private final JLabel			heading, result;
	private final CustomButtonSmall	x, y, origin, go;
	private final Function			functionBase;
	private Function				functionMirrored;
	
	public PanelFunctionActionsMenu_Mirror(Function f) {
		
		logger.info("Initializing new PanelFunctionActionsMenu_Mirror"); //$NON-NLS-1$
		
		functionBase = f;
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 0, 400, 50);
		add(heading);

		result = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
		result.setBounds(100, 90, 400, 30);
		add(result);
		
		go = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_go")); //$NON-NLS-1$
		go.setLocation(250, 130);
		go.setEnabled(false);
		go.addActionListener(e -> {
			Controller.getInstance().getLogicController().storeFunction(functionMirrored);
			FrameMain.getInstance().panelFunctionActionsMenu(functionMirrored,
					FrameMain.getInstance().getPanelCenter());
		});
		add(go);
		
		x = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_x-axis")); //$NON-NLS-1$
		x.setLocation(100, 55);
		x.addActionListener(e -> {
			functionMirrored = functionBase.mirrorX();
			result.setText(functionMirrored.toString());
			go.setEnabled(true);
		});
		add(x);
		
		y = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_y-axis")); //$NON-NLS-1$
		y.setLocation(250, 55);
		y.addActionListener(e -> {
			functionMirrored = functionBase.mirrorY();
			result.setText(functionMirrored.toString());
			go.setEnabled(true);
		});
		add(y);

		origin = new CustomButtonSmall(Strings.getStringAsHTML("PanelFunctionActionsMenu_Mirror.button_origin")); //$NON-NLS-1$
		origin.setLocation(400, 55);
		origin.addActionListener(e -> {
			functionMirrored = functionBase.mirrorOrigin();
			result.setText(functionMirrored.toString());
			go.setEnabled(true);
		});
		add(origin);
		
		setDefaultComponent(x);
	}
	
}
