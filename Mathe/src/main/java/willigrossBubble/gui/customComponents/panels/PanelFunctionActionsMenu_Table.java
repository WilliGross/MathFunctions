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

import willigrossBubble.Function;
import willigrossBubble.Strings;
import willigrossBubble.Utility;
import willigrossBubble.Validations;
import willigrossBubble.gui.FocusAdapter_SelectAll;
import willigrossBubble.gui.FrameMain;

public class PanelFunctionActionsMenu_Table extends RequestFocusForDefaultComponentPanel {

	private static final long				serialVersionUID	= 1L;
	private static final int				MAX_VALUE			= 100000, MIN_VALUE = -100000;
	private final JLabel					heading, startLabel, endLabel, stepLabel, resultLabel;
	private final JTextField				start, end, step;
	private final DefaultListModel<String>	listModel;
	private final JList<String>				result;
	private final JScrollPane				resultScrollPane;
	private final Function					function;
	private final String					errorNumber;
	private String							valueWarn;
	
	public PanelFunctionActionsMenu_Table(Function f) {

		errorNumber = Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.errorNumber"); //$NON-NLS-1$

		valueWarn = Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.error_enterInterval"); //$NON-NLS-1$
		valueWarn = valueWarn.replace("$MIN_VALUE$", String.valueOf(MIN_VALUE)); //$NON-NLS-1$
		valueWarn = valueWarn.replace("$MAX_VALUE$", String.valueOf(MAX_VALUE)); //$NON-NLS-1$

		function = f;

		setLayout(null);

		final FocusAdapter_SelectAll focusAdapter_SelectAll = new FocusAdapter_SelectAll();
		final KeyListeneR keyListener = new KeyListeneR();

		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);

		startLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_start"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		startLabel.setBounds(50, 40, 45, 30);
		add(startLabel);

		endLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_end"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		endLabel.setBounds(200, 40, 45, 30);
		add(endLabel);

		stepLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_step"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		stepLabel.setBounds(350, 40, 45, 30);
		add(stepLabel);

		start = new JTextField(Strings.getString("PanelFunctionActionsMenu_Table.textField_start")); //$NON-NLS-1$
		start.setBounds(100, 40, 100, 30);
		start.addFocusListener(focusAdapter_SelectAll);
		start.addKeyListener(keyListener);
		add(start);

		end = new JTextField(Strings.getString("PanelFunctionActionsMenu_Table.textField_end")); //$NON-NLS-1$
		end.setBounds(250, 40, 100, 30);
		end.addFocusListener(focusAdapter_SelectAll);
		end.addKeyListener(keyListener);
		add(end);

		step = new JTextField(Strings.getString("PanelFunctionActionsMenu_Table.textField_step")); //$NON-NLS-1$
		step.setBounds(400, 40, 100, 30);
		step.addFocusListener(focusAdapter_SelectAll);
		step.addKeyListener(keyListener);
		add(step);

		resultLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_result")); //$NON-NLS-1$
		resultLabel.setBounds(50, 90, 45, 30);
		add(resultLabel);

		listModel = new DefaultListModel<>();
		result = new JList<>(listModel);
		result.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result.setSelectionModel(new DefaultListSelectionModel());
		result.addListSelectionListener(e -> result.clearSelection());

		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 160);
		add(resultScrollPane);

		setDefaultComponent(start);
	}

	private boolean validateStart() {
		final String errorNumberStart = errorNumber.replace("$FIELD$", "start"); //$NON-NLS-1$ //$NON-NLS-2$
		final String valueWarnStart = valueWarn.replace("$FIELD$", "start"); //$NON-NLS-1$ //$NON-NLS-2$
		boolean startValidationOne = false, startValidationTwo = false;

		if (start.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_start"))) //$NON-NLS-1$
			return true;

		if (Validations.canConvertToNumber(start.getText())) {
			if ((Utility.readDoubleFromStringInput(start.getText()) > MAX_VALUE)
					|| (Utility.readDoubleFromStringInput(start.getText()) < MIN_VALUE)) {
				if (!listModel.contains(valueWarnStart))
					listModel.addElement(valueWarnStart);
				startValidationTwo = false;
			} else {
				listModel.removeElement(valueWarnStart);
				startValidationTwo = true;
			}
			listModel.removeElement(errorNumberStart);
			startValidationOne = true;
		} else {
			if (!listModel.contains(errorNumberStart))
				listModel.addElement(errorNumberStart);
			startValidationOne = false;
		}

		if (startValidationOne && startValidationTwo)
			return true;
		return false;
	}

	private boolean validateEnd() {
		final String errorNumberEnd = errorNumber.replace("$FIELD$", "end"); //$NON-NLS-1$ //$NON-NLS-2$
		final String valueWarnEnd = valueWarn.replace("$FIELD$", "end"); //$NON-NLS-1$ //$NON-NLS-2$
		boolean endValidationOne = false, endValidationTwo = false;

		if (end.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_end"))) //$NON-NLS-1$
			return true;

		if (Validations.canConvertToNumber(end.getText())) {
			if ((Utility.readDoubleFromStringInput(end.getText()) > MAX_VALUE)
					|| (Utility.readDoubleFromStringInput(end.getText()) < MIN_VALUE)) {
				if (!listModel.contains(valueWarnEnd))
					listModel.addElement(valueWarnEnd);
				endValidationTwo = false;
			} else {
				listModel.removeElement(valueWarnEnd);
				endValidationTwo = true;
			}
			listModel.removeElement(errorNumberEnd);
			endValidationOne = true;
		} else {
			if (!listModel.contains(errorNumberEnd))
				listModel.addElement(errorNumberEnd);
			endValidationOne = false;
		}

		if (endValidationOne && endValidationTwo)
			return true;
		return false;
	}

	private boolean validateStep() {
		final String errorNumberStep = errorNumber.replace("$FIELD$", "step"); //$NON-NLS-1$ //$NON-NLS-2$
		String valueWarnStep = valueWarn.replace("$FIELD$", "step"); //$NON-NLS-1$ //$NON-NLS-2$
		boolean stepValidationOne = false, stepValidationTwo = false;

		if (step.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_step"))) //$NON-NLS-1$
			return true;

		if (Validations.canConvertToNumber(step.getText())) {
			if ((Utility.readDoubleFromStringInput(step.getText()) == 0)
					|| (Utility.readDoubleFromStringInput(step.getText()) > MAX_VALUE)
					|| (Utility.readDoubleFromStringInput(step.getText()) < MIN_VALUE)) {
				if (Utility.readDoubleFromStringInput(step.getText()) == 0)
					valueWarnStep = Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.error_stepZero"); //$NON-NLS-1$
				if (!listModel.contains(valueWarnStep))
					listModel.addElement(valueWarnStep);
				stepValidationTwo = false;
			} else {
				listModel.removeElement(valueWarnStep);
				stepValidationTwo = true;
			}
			listModel.removeElement(errorNumberStep);
			stepValidationOne = true;
		} else {
			if (!listModel.contains(errorNumberStep))
				listModel.addElement(errorNumberStep);
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

			final boolean startValidation = validateStart(), endValidation = validateEnd(),
					stepValidation = validateStep();

			//validate start
			if (!start.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_start"))) //$NON-NLS-1$
				if (startValidation)
					start.setBorder(new LineBorder(Color.GRAY));
				else
					start.setBorder(new LineBorder(Color.RED, 2));
				
			//validate end
			if (!end.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_end"))) //$NON-NLS-1$
				if (endValidation)
					end.setBorder(new LineBorder(Color.GRAY));
				else
					end.setBorder(new LineBorder(Color.RED, 2));
				
			//validate step
			if (!step.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_step"))) //$NON-NLS-1$
				if (stepValidation)
					step.setBorder(new LineBorder(Color.GRAY));
				else
					step.setBorder(new LineBorder(Color.RED, 2));
				
			//calculate and display value table
			if (!start.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_start")) //$NON-NLS-1$
					&& !end.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_end")) //$NON-NLS-1$
					&& !step.getText().equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_step")) //$NON-NLS-1$
					&& startValidation && endValidation && stepValidation) {
				final double startValue = Utility.readDoubleFromStringInput(start.getText()),
						endValue = Utility.readDoubleFromStringInput(end.getText()),
						stepValue = Utility.readDoubleFromStringInput(step.getText());
				final String[] tableAsArray = function.table(startValue, endValue, stepValue);
				for (final String element : tableAsArray)
					listModel.addElement(element);
			}

		}
	}

}
