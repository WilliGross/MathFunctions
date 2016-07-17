package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.ExponentialFunction;
import willigrossBubble.Function;
import willigrossBubble.InvalidPointConfigurationException;
import willigrossBubble.LinearFunction;
import willigrossBubble.Point;
import willigrossBubble.Strings;
import willigrossBubble.Utility;
import willigrossBubble.Validations;
import willigrossBubble.gui.FocusAdapter_SelectAll;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;


public class PanelCreateFunction_ThroughTwoPoints extends RequestFocusForDefaultComponentPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField p1x, p1y, p2x, p2y;
	private JLabel p1, p2, result, heading;
	private CustomButtonSmall go;
	private Function function;
	private FunctionType type;

	
	
	public PanelCreateFunction_ThroughTwoPoints(FunctionType type) {
		
		this.type = type;
		
		setLayout(null);
		
		FocusAdapter_SelectAll focusAdapter_SelectAll = new FocusAdapter_SelectAll();
		
		KeyAdapter keyListener = new KeyListeneR();
		
		if (type == FunctionType.EXPONENTIAL)
			heading = new JLabel(Strings.getString("PanelCreateFunction_ThroughTwoPoints.label_heading_exponential"), SwingConstants.CENTER); //$NON-NLS-1$
		else if (type == FunctionType.LINEAR)
			heading = new JLabel(Strings.getString("PanelCreateFunction_ThroughTwoPoints.label_heading_linear"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);
		
		
		p1 = new JLabel(Strings.getString("PanelCreateFunction_ThroughTwoPoints.label_pointOne"), SwingConstants.RIGHT); //$NON-NLS-1$
		p1.setBounds(40, 40, 50, 30);
		add(p1);
		
		p1x = new JTextField(Strings.getString("PanelCreateFunction_ThroughTwoPoints.textField_pointOne_x")); //$NON-NLS-1$
		p1x.setBounds(100, 40, 190, 30);
		p1x.addFocusListener(focusAdapter_SelectAll);
		p1x.addKeyListener(keyListener);
		add(p1x);
		
		p1y = new JTextField(Strings.getString("PanelCreateFunction_ThroughTwoPoints.textField_pointOne_y")); //$NON-NLS-1$
		p1y.setBounds(310, 40, 190, 30);
		p1y.addFocusListener(focusAdapter_SelectAll);
		p1y.addKeyListener(keyListener);
		add(p1y);
		
		
		p2 = new JLabel(Strings.getString("PanelCreateFunction_ThroughTwoPoints.label_pointTwo"), SwingConstants.RIGHT); //$NON-NLS-1$
		p2.setBounds(40, 75, 50, 30);
		add(p2);	
		
		p2x = new JTextField(Strings.getString("PanelCreateFunction_ThroughTwoPoints.textField_pointTwo_x")); //$NON-NLS-1$
		p2x.setBounds(100, 75, 190, 30);
		p2x.addFocusListener(focusAdapter_SelectAll);
		p2x.addKeyListener(keyListener);
		add(p2x);
		
		p2y = new JTextField(Strings.getString("PanelCreateFunction_ThroughTwoPoints.textField_pointTwo_y")); //$NON-NLS-1$
		p2y.setBounds(310, 75, 190, 30);
		p2y.addFocusListener(focusAdapter_SelectAll);
		p2y.addKeyListener(keyListener);
		add(p2y);
		
		result = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
		result.setBounds(100, 120, 400, 30);
		add(result);
		
		go = new CustomButtonSmall(Strings.getString("PanelCreateFunction_ThroughTwoPoints.button_go")); //$NON-NLS-1$
		go.setLocation(250, 160);
		go.setEnabled(false);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMain.getInstance().panelFunctionActionsMenu(
						FrameMain.getInstance().getMainLogic().getFunction(
								FrameMain.getInstance().getMainLogic().storeFunction(function)), 
						FrameMain.getInstance().getPanelCenter());
			}
			
		});
		add(go);
		
		setDefaultComponent(p1x);
	}
	
	private void calcFunction() throws Exception {
		
		try {
			double 	px = Utility.readDoubleFromStringInput(p1x.getText()),
					py = Utility.readDoubleFromStringInput(p1y.getText()),
					qx = Utility.readDoubleFromStringInput(p2x.getText()),
					qy = Utility.readDoubleFromStringInput(p2y.getText());
			Point p = new Point(px, py), q = new Point(qx, qy);
			
			if (type == FunctionType.EXPONENTIAL)
				function = ExponentialFunction.createThroughPoints(p, q);
			else if (type == FunctionType.LINEAR)
				function = LinearFunction.createThroughPoints(p, q);
			//TODO Display warning when p.equals(q)
		} catch (IllegalArgumentException | InvalidPointConfigurationException e) {
			result.setText(e.getMessage());
			result.setForeground(Color.RED);
			if (e instanceof InvalidPointConfigurationException && ((InvalidPointConfigurationException) e).hasTooltip())
				result.setToolTipText(((InvalidPointConfigurationException) e).getTooltip());
			throw new Exception(Strings.getString("PanelCreateFunction_ThroughTwoPoints.error_calculation")); //$NON-NLS-1$
		}
	}
	
	private class KeyListeneR extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			
			result.setText(""); //$NON-NLS-1$
			
			JTextField source = (JTextField) e.getSource();
			if (!Validations.canConvertToNumber(source.getText())) {
				source.setBorder(new LineBorder(Color.RED, 2));
				go.setEnabled(false);
			} else {
				source.setBorder(new LineBorder(Color.GRAY));
				if (	Validations.canConvertToNumber(p1x.getText()) && 
						Validations.canConvertToNumber(p1y.getText()) &&
						Validations.canConvertToNumber(p2x.getText()) &&
						Validations.canConvertToNumber(p2y.getText())) {
					go.setEnabled(true);
					try {
						calcFunction();
						result.setText(function.toString());
						result.setForeground(Color.BLACK);
					} catch (@SuppressWarnings("unused") Exception ex) {
						go.setEnabled(false);
					}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				go.doClick();
			}
		}
	}
	
	public enum FunctionType {
		EXPONENTIAL, LINEAR
	}
	
}
