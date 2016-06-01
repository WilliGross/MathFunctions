package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.Function;
import willigrossBubble.Validations;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;


public class PanelCreateFunction_TypeFunction extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel f;
	private JTextField function;
	private CustomButtonSmall go;
	
	
	public PanelCreateFunction_TypeFunction() {
		setLayout(null);
		
		f = new JLabel("f(x) = ", SwingConstants.CENTER);
		f.setBounds(50, 40, 50, 30);
		add(f);
		
		function = new JTextField();
		function.setBounds(100, 40, 400, 30);
//		function.setBorder(new LineBorder(Color.RED, 2));
		function.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (!Validations.doesExpressionContainValidCharacters(function.getText())) {
					function.setBorder(new LineBorder(Color.RED, 2));
					go.setEnabled(false);
				} else {
					function.setBorder(new LineBorder(Color.BLACK));
					go.setEnabled(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					go.doClick();
				}
			}
		});
		add(function);
		
		go = new CustomButtonSmall("Go");
		go.setLocation(250, 85);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().getMainLogic().storeFunction(new Function(function.getText()));
			}
		});
		add(go);
	}
	
}
