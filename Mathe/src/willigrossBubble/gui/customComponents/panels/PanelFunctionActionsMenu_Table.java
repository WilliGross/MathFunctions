package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import willigrossBubble.Function;
import willigrossBubble.Utility;
import willigrossBubble.Validations;
import willigrossBubble.gui.FocusAdapter_SelectAll;
import willigrossBubble.gui.FrameMain;


public class PanelFunctionActionsMenu_Table extends RequestFocusForDefaultComponentPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_VALUE = 100000, MIN_VALUE = -100000;
	private JLabel heading, startLabel, endLabel, stepLabel, resultLabel;
	private JTextField start, end, step;
	private DefaultListModel<String> listModel;
	private JList<String> result;
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
		
		listModel = new DefaultListModel<>();
		result = new JList<>(listModel);
		result.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result.setSelectionModel(new DefaultListSelectionModel());
		result.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				result.clearSelection();
			}
		});
		
		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 160);
		add(resultScrollPane);
		
		setDefaultComponent(start);
	}
	
	private boolean validateStart() {
		String errorStart = "Enter a number for 'start'!";
		String 	valueWarnStart 	= "Enter a value between " + MIN_VALUE + " and " + MAX_VALUE + " for 'start'!";
		boolean startValidationOne = false, startValidationTwo = false;
		
		if (start.getText().equals("start value"))
			return true;
		
		if (Validations.canConvertToNumber(start.getText())) {
			if (Utility.readDoubleFromStringInput(start.getText()) > MAX_VALUE || Utility.readDoubleFromStringInput(start.getText()) < MIN_VALUE) {
				if (!listModel.contains(valueWarnStart))
					listModel.addElement(valueWarnStart);
				startValidationTwo = false;
			} else {
				listModel.removeElement(valueWarnStart);
				startValidationTwo = true;
			}
			listModel.removeElement(errorStart);
			startValidationOne = true;
		} else {
			if (!listModel.contains(errorStart))
				listModel.addElement(errorStart);
			startValidationOne = false;
		}
		
		if (startValidationOne && startValidationTwo)
			return true;
		return false;
	}
	
	private boolean validateEnd() {
		String errorEnd = "Enter a number for 'end'!";
		String valueWarnEnd 	= "Enter a value between " + MIN_VALUE + " and " + MAX_VALUE + " for 'end'!";
		boolean endValidationOne = false, endValidationTwo = false;
		
		if (end.getText().equals("end value"))
			return true;
		
		if (Validations.canConvertToNumber(end.getText())) {
			if (Utility.readDoubleFromStringInput(end.getText()) > MAX_VALUE || Utility.readDoubleFromStringInput(end.getText()) < MIN_VALUE) {
				if (!listModel.contains(valueWarnEnd))
					listModel.addElement(valueWarnEnd);
				endValidationTwo = false;
			} else {
				listModel.removeElement(valueWarnEnd);
				endValidationTwo = true;
			}
			listModel.removeElement(errorEnd);
			endValidationOne = true;
		} else {
			if (!listModel.contains(errorEnd))
				listModel.addElement(errorEnd);
			endValidationOne = false;
		}
		
		if (endValidationOne && endValidationTwo)
			return true;
		return false;
	}
	
	private boolean validateStep() {
		String errorStep = "Enter a number for 'step'!";
		String valueWarnStep 	= "Step mustn't be 0!";
		boolean stepValidationOne = false, stepValidationTwo = false;
		
		if (step.getText().equals("step"))
			return true;
		
		if (Validations.canConvertToNumber(step.getText())) {
			if (Utility.readDoubleFromStringInput(step.getText()) == 0) {
				if (!listModel.contains(valueWarnStep))
					listModel.addElement(valueWarnStep);
				stepValidationTwo = false;
			} else {
				listModel.removeElement(valueWarnStep);
				stepValidationTwo = true;
			}
			listModel.removeElement(errorStep);
			stepValidationOne = true;
		} else {
			if (!listModel.contains(errorStep))
				listModel.addElement(errorStep);
			stepValidationOne = false;
		}
		
		if (stepValidationOne && stepValidationTwo)
			return true;
		return false;
	}
	
	private class KeyListeneR extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			
			listModel.clear();
			
			boolean startValidation = validateStart(), endValidation = validateEnd(), stepValidation = validateStep();
			
			//validate start
			if (!start.getText().equals("start value"))
				if (startValidation)
					start.setBorder(new LineBorder(Color.GRAY));
				else
					start.setBorder(new LineBorder(Color.RED, 2));
			
			//validate end
			if (!end.getText().equals("end value"))
				if (endValidation)
					end.setBorder(new LineBorder(Color.GRAY));
				else
					end.setBorder(new LineBorder(Color.RED, 2));
			
			//validate step
			if (!step.getText().equals("step"))
				if (stepValidation)
					step.setBorder(new LineBorder(Color.GRAY));
				else
					step.setBorder(new LineBorder(Color.RED, 2));
			
			//calculate and display value table
			if (!start.getText().equals("start value") && !end.getText().equals("end value") && !step.getText().equals("step") && startValidation && endValidation && stepValidation) {
				double 	startValue 	= Utility.readDoubleFromStringInput(start.getText()	), 
						endValue 	= Utility.readDoubleFromStringInput(end.getText()	), 
						stepValue 	= Utility.readDoubleFromStringInput(step.getText()	);
				String[] tableAsArray = function.table(startValue, endValue, stepValue);
				for (int i = 0; i < tableAsArray.length; i++)
					listModel.addElement(tableAsArray[i]);
			}
			
		}
	}
	
}
