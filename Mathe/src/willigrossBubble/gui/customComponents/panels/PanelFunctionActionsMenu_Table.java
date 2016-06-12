package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.Function;
import willigrossBubble.Utility;
import willigrossBubble.Validations;
import willigrossBubble.gui.FocusAdapter_SelectAll;
import willigrossBubble.gui.FrameMain;


public class PanelFunctionActionsMenu_Table extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel heading, startLabel, endLabel, stepLabel, resultLabel;
	private JTextField start, end, step;
	private JTextArea result;
	private JScrollPane resultScrollPane;
	private Function function;
	
	public PanelFunctionActionsMenu_Table(Function f) {
		
		function = f;
		
		setLayout(null);
		
		FocusAdapter_SelectAll focusAdapter_SelectAll = new FocusAdapter_SelectAll();
		KeyListeneR keyListener = new KeyListeneR();
		
		heading = new JLabel("Calculate a value table", SwingConstants.CENTER);
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);
		
		startLabel = new JLabel("Start:", SwingConstants.RIGHT);
		startLabel.setBounds(50, 40, 45, 30);
		add(startLabel);
		
		endLabel = new JLabel("End:", SwingConstants.RIGHT);
		endLabel.setBounds(200, 40, 45, 30);
		add(endLabel);
		
		stepLabel = new JLabel("Step:", SwingConstants.RIGHT);
		stepLabel.setBounds(350, 40, 45, 30);
		add(stepLabel);
		
		start = new JTextField("start value");
		start.setBounds(100, 40, 100, 30);
		start.addFocusListener(focusAdapter_SelectAll);
		start.addKeyListener(keyListener);
		add(start);
		
		end = new JTextField("end value");
		end.setBounds(250, 40, 100, 30);
		end.addFocusListener(focusAdapter_SelectAll);
		end.addKeyListener(keyListener);
		add(end);
		
		step = new JTextField("step");
		step.setBounds(400, 40, 100, 30);
		step.addFocusListener(focusAdapter_SelectAll);
		step.addKeyListener(keyListener);
		add(step);
		
		resultLabel = new JLabel("Result:");
		resultLabel.setBounds(50, 90, 45, 30);
		add(resultLabel);
		
		result = new JTextArea();
		result.setEditable(false);
		
		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 160);
		add(resultScrollPane);
		
	}
	
	private class KeyListeneR extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			result.setText("");
			JTextField source = (JTextField) e.getSource();
			if (!Validations.canConvertToNumber(source.getText())) {
				source.setBorder(new LineBorder(Color.RED, 2));
			} else {
				source.setBorder(new LineBorder(Color.GRAY));
				if (Validations.canConvertToNumber(start.getText()) && Validations.canConvertToNumber(end.getText()) && Validations.canConvertToNumber(step.getText())) {
					double 	startValue 	= Utility.readDoubleFromStringInput(start.getText()	), 
							endValue 	= Utility.readDoubleFromStringInput(end.getText()	), 
							stepValue 	= Utility.readDoubleFromStringInput(step.getText()	);
					String[] tableAsArray = function.table(startValue, endValue, stepValue);
					for (int i = 0; i < tableAsArray.length; i++) {
						result.append(tableAsArray[i] + "\n");
					}
				}
			}
		}
	}
	
}