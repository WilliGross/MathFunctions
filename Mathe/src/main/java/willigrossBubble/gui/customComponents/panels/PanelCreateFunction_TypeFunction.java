package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.Function;
import willigrossBubble.Strings;
import willigrossBubble.Validations;
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
		heading.setBounds(100, 0, 400, 30);
		heading.setFont(FrameMain.getGlobalFont());
		add(heading);

		f = new JLabel("f(x) = ", SwingConstants.CENTER); //$NON-NLS-1$
		f.setBounds(50, 40, 50, 30);
		add(f);

		function = new JTextField();
		function.setBounds(100, 40, 400, 30);
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
		go.setLocation(250, 85);
		go.setEnabled(false);
		go.addActionListener(
				e -> FrameMain.getInstance()
						.panelFunctionActionsMenu(
								FrameMain.getInstance().getMainLogic()
										.getFunction(FrameMain.getInstance().getMainLogic()
												.storeFunction(new Function(function.getText()))),
								FrameMain.getInstance().getPanelCenter()));
		add(go);

		setDefaultComponent(function);
	}

}
