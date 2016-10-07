package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.core.Controller;
import willigrossBubble.core.logic.Function;
import willigrossBubble.core.logic.Validations;
import willigrossBubble.data.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;

public class PanelCreateFunction_TypeFunction extends RequestFocusForDefaultComponentPanel {

	private static final long		serialVersionUID	= 1L;
	private final JLabel			f;
	private final JTextField		function;
	private final CustomButtonSmall	go;
	private final JLabel			heading;
	
	public PanelCreateFunction_TypeFunction() {

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
		go.addActionListener(
				e -> ((FrameMain) Controller.getInstance().getGUIController()).panelFunctionActionsMenu(
						Controller.getInstance().getLogicController()
								.getFunction(Controller.getInstance().getLogicController()
										.storeFunction(new Function(function.getText()))),
						((FrameMain) Controller.getInstance().getGUIController()).getPanelCenter()));
		add(go);

		setDefaultComponent(function);
	}

}
