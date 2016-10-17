package willigross.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.Controller;
import willigross.core.logic.Function;
import willigross.core.logic.Validations;
import willigross.data.Strings;
import willigross.gui.FrameMain;
import willigross.gui.customComponents.buttons.CustomButtonSmall;

public class PanelCreateFunction_TypeFunction extends RequestFocusForDefaultComponentPanel {
	
	private static final long		serialVersionUID	= 1L;
	private static final Logger		logger				= LoggerFactory
			.getLogger(PanelCreateFunction_TypeFunction.class);
	private final JLabel			f;
	private final JTextField		function;
	private final CustomButtonSmall	go;
	private final JLabel			heading;

	public PanelCreateFunction_TypeFunction() {
		
		logger.info("Initializing new PanelCreateFunction_TypeFunction"); //$NON-NLS-1$

		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelCreateFunction_TypeFunction.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setBounds(100, 0, 400, 50);
		heading.setFont(FrameMain.getHeadingFont());
		add(heading);
		
		f = new JLabel(Strings.getString("PanelCreateFunction_TypeFunction.label_f(x)"), SwingConstants.CENTER); //$NON-NLS-1$
		f.setBounds(50, 55, 55, 30);
		add(f);
		
		function = new JTextField();
		function.setBounds(100, 55, 400, 30);
		function.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (!Validations.doesExpressionContainValidCharacters(function.getText())) {
					function.setBorder(new LineBorder(Color.RED, 2));
					go.setEnabled(false);
				} else {
					function.setBorder(new LineBorder(Color.GRAY));
					go.setEnabled(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					go.doClick();
			}
		});
		add(function);
		
		go = new CustomButtonSmall(Strings.getStringAsHTML("PanelCreateFunction_TypeFunction.button_go")); //$NON-NLS-1$
		go.setLocation(250, 100);
		go.setEnabled(false);
		go.addActionListener(e -> {
			logger.info(
					"Creating new function from user input, passing it to logic controller and calling FunctionActionsMenu"); //$NON-NLS-1$
			FrameMain.getInstance().panelFunctionActionsMenu(
					Controller.getInstance().getLogicController()
							.getFunction(Controller.getInstance().getLogicController()
									.storeFunction(new Function(function.getText()))),
					FrameMain.getInstance().getPanelCenter());
		});
		add(go);
		
		setDefaultComponent(function);
	}
	
}
