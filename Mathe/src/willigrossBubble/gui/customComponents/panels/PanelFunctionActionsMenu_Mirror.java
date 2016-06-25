package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.Function;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;


public class PanelFunctionActionsMenu_Mirror extends RequestFocusForDefaultComponentPanel {

	private static final long serialVersionUID = 1L;
	private JLabel heading, result;
	private CustomButtonSmall x, y, origin, go;
	private Function functionBase, functionMirrored;
	
	public PanelFunctionActionsMenu_Mirror(Function f) {
		
		functionBase = f;
		setLayout(null);
		
		heading = new JLabel("Create mirrored function", SwingConstants.CENTER);
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);
		
		x = new CustomButtonSmall("x - axis");
		x.setLocation(100, 40);
		x.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				functionMirrored = functionBase.mirrorX();
				result.setText(functionMirrored.toString());
				go.setEnabled(true);
			}
		});
		add(x);
		
		y = new CustomButtonSmall("y - axis");
		y.setLocation(250, 40);
		y.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				functionMirrored = functionBase.mirrorY();
				result.setText(functionMirrored.toString());
				go.setEnabled(true);
			}
		});
		add(y);
		
		origin = new CustomButtonSmall("origin");
		origin.setLocation(400, 40);
		origin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				functionMirrored = functionBase.mirrorOrigin();
				result.setText(functionMirrored.toString());
				go.setEnabled(true);
			}
		});
		add(origin);
		
		result = new JLabel("", SwingConstants.CENTER);
		result.setBounds(100, 90, 400, 30);
		add(result);
		
		go = new CustomButtonSmall("Go");
		go.setLocation(250, 130);
		go.setEnabled(false);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().getMainLogic().storeFunction(functionMirrored);
				FrameMain.getInstance().panelFunctionActionsMenu(functionMirrored, FrameMain.getInstance().getPanelCenter());
			}
		});
		add(go);
		
		setDefaultComponent(x);
	}
	
}
