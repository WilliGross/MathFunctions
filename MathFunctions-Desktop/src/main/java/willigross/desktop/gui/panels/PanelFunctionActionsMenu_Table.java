package willigross.desktop.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.data.UtilityData;
import willigross.core.logic.Function;
import willigross.core.logic.Validations;
import willigross.core.logic.ValueTablePresentation;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.FocusAdapter_SelectAll;
import willigross.desktop.gui.FrameMain;

public class PanelFunctionActionsMenu_Table extends RequestFocusForDefaultComponentPanel {

	private static final long								serialVersionUID	= 1L;

	private static final Logger								logger				= LoggerFactory
			.getLogger(PanelFunctionActionsMenu_Point.class);

	private static final int								MAX_VALUE			= 100000, MIN_VALUE = -100000;
	private final JLabel									heading, startLabel, endLabel, stepLabel, resultLabel;
	private final JTextField								start, end, step;
	private final DefaultListModel<ValueTablePresentation>	listModel;
	private final JList<ValueTablePresentation>				result;
	private final JScrollPane								resultScrollPane;
	private final Function									function;
	private final String									errorNumber;
	private String											valueWarn;
	private final ValueTablePresentation					errorNumberStart, valueWarnStart, errorNumberEnd,
			valueWarnEnd, errorNumberStep, valueWarnStep1, valueWarnStep2;

	public PanelFunctionActionsMenu_Table(Function f) {

		logger.info("Initializing new PanelFunctionActionsMenu_Table for function {}", f); //$NON-NLS-1$

		errorNumber = Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.errorNumber"); //$NON-NLS-1$

		valueWarn = Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.error_enterInterval"); //$NON-NLS-1$
		valueWarn = valueWarn.replace("$MIN_VALUE$", String.valueOf(MIN_VALUE)); //$NON-NLS-1$
		valueWarn = valueWarn.replace("$MAX_VALUE$", String.valueOf(MAX_VALUE)); //$NON-NLS-1$
		
		errorNumberStart = new ValueTablePresentation(errorNumber.replace("$FIELD$", "start")); //$NON-NLS-1$ //$NON-NLS-2$
		valueWarnStart = new ValueTablePresentation(valueWarn.replace("$FIELD$", "start")); //$NON-NLS-1$ //$NON-NLS-2$
		errorNumberEnd = new ValueTablePresentation(errorNumber.replace("$FIELD$", "end")); //$NON-NLS-1$ //$NON-NLS-2$
		valueWarnEnd = new ValueTablePresentation(valueWarn.replace("$FIELD$", "end")); //$NON-NLS-1$ //$NON-NLS-2$
		errorNumberStep = new ValueTablePresentation(errorNumber.replace("$FIELD$", "step")); //$NON-NLS-1$ //$NON-NLS-2$
		valueWarnStep1 = new ValueTablePresentation(valueWarn.replace("$FIELD$", "step")); //$NON-NLS-1$ //$NON-NLS-2$
		valueWarnStep2 = new ValueTablePresentation(
				Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.error_stepZero")); //$NON-NLS-1$

		function = f;

		setLayout(null);

		final FocusAdapter_SelectAll focusAdapter_SelectAll = new FocusAdapter_SelectAll();
		final KeyListeneR keyListener = new KeyListeneR();

		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);

		startLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_start"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		startLabel.setBounds(50, 55, 45, 30);
		add(startLabel);

		endLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_end"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		endLabel.setBounds(200, 55, 45, 30);
		add(endLabel);

		stepLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_step"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		stepLabel.setBounds(350, 55, 45, 30);
		add(stepLabel);

		start = new JTextField(Strings.getString("PanelFunctionActionsMenu_Table.textField_start")); //$NON-NLS-1$
		start.setBounds(100, 55, 100, 30);
		start.addFocusListener(focusAdapter_SelectAll);
		start.addKeyListener(keyListener);
		add(start);

		end = new JTextField(Strings.getString("PanelFunctionActionsMenu_Table.textField_end")); //$NON-NLS-1$
		end.setBounds(250, 55, 100, 30);
		end.addFocusListener(focusAdapter_SelectAll);
		end.addKeyListener(keyListener);
		add(end);

		step = new JTextField(Strings.getString("PanelFunctionActionsMenu_Table.textField_step")); //$NON-NLS-1$
		step.setBounds(400, 55, 100, 30);
		step.addFocusListener(focusAdapter_SelectAll);
		step.addKeyListener(keyListener);
		add(step);

		resultLabel = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Table.label_result"), //$NON-NLS-1$
				SwingConstants.RIGHT);
		resultLabel.setBounds(40, 105, 45, 30);
		add(resultLabel);

		listModel = new DefaultListModel<>();
		result = new JList<ValueTablePresentation>(listModel) {

			private static final long serialVersionUID = 1L;

			@Override
			public String getToolTipText(MouseEvent event) {
				final int index = locationToIndex(event.getPoint());
				final ValueTablePresentation item = getModel().getElementAt(index);
				return item.getUnroundedValue();
			}

		};
		result.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result.setSelectionModel(new DefaultListSelectionModel());
		result.addListSelectionListener(e -> result.clearSelection());
		result.setFont(FrameMain.getMonospacedFont().deriveFont(Font.BOLD));

		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 105, 400, 150);
		add(resultScrollPane);

		setDefaultComponent(start);
	}

	private boolean validateStart() {

		logger.info("Validating start value..."); //$NON-NLS-1$

		boolean startValidationOne = false, startValidationTwo = false;
		final String text = start.getText();

		if (text.equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_start"))) //$NON-NLS-1$
			return true;

		if (Validations.canConvertToNumber(text)) {
			final double value = UtilityData.readDoubleFromStringInput(text);
			if ((value > MAX_VALUE) || (value < MIN_VALUE)) {
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

		if (startValidationOne && startValidationTwo) {
			logger.info("...valid"); //$NON-NLS-1$
			return true;
		}
		logger.info("...invalid"); //$NON-NLS-1$
		return false;
	}

	private boolean validateEnd() {

		logger.info("Validating end value..."); //$NON-NLS-1$

		boolean endValidationOne = false, endValidationTwo = false;
		final String text = end.getText();

		if (text.equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_end"))) //$NON-NLS-1$
			return true;

		if (Validations.canConvertToNumber(text)) {
			final double value = UtilityData.readDoubleFromStringInput(text);
			if ((value > MAX_VALUE) || (value < MIN_VALUE)) {
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

		if (endValidationOne && endValidationTwo) {
			logger.info("...valid"); //$NON-NLS-1$
			return true;
		}
		logger.info("...invalid"); //$NON-NLS-1$
		return false;
	}

	private boolean validateStep() {

		logger.info("Validating step value..."); //$NON-NLS-1$

		boolean stepValidationOne = false, stepValidationTwo = false;
		final String text = step.getText();

		if (text.equals(Strings.getString("PanelFunctionActionsMenu_Table.textField_step"))) //$NON-NLS-1$
			return true;

		if (Validations.canConvertToNumber(text)) {
			final double value = UtilityData.readDoubleFromStringInput(text);

			if ((value > MAX_VALUE) || (value < MIN_VALUE)) {
				if (!listModel.contains(valueWarnStep1)) {
					listModel.addElement(valueWarnStep1);
					stepValidationTwo = false;
				}
			} else {
				listModel.removeElement(valueWarnStep1);
				stepValidationTwo = true;
			}

			if (value == 0) {
				if (!listModel.contains(valueWarnStep2)) {
					listModel.addElement(valueWarnStep2);
					stepValidationTwo = false;
				}
			} else {
				listModel.removeElement(valueWarnStep2);
				stepValidationTwo = true;
			}

			listModel.removeElement(errorNumberStep);
			stepValidationOne = true;
		} else {
			if (!listModel.contains(errorNumberStep))
				listModel.addElement(errorNumberStep);
			stepValidationOne = false;
		}

		if (stepValidationOne && stepValidationTwo) {
			logger.info("...valid"); //$NON-NLS-1$
			return true;
		}
		logger.info("...invalid"); //$NON-NLS-1$
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
				logger.info("All values valid"); //$NON-NLS-1$
				final double startValue = UtilityData.readDoubleFromStringInput(start.getText()),
						endValue = UtilityData.readDoubleFromStringInput(end.getText()),
						stepValue = UtilityData.readDoubleFromStringInput(step.getText());
				logger.info("Comissioning value table calculation"); //$NON-NLS-1$
				final ValueTablePresentation[] tableAsArray = function.table(startValue, endValue, stepValue);
				int equalsIndex = 0;
				for (final ValueTablePresentation element : tableAsArray)
					if (element.getRoundedValue().indexOf('=') > equalsIndex)
						equalsIndex = element.getRoundedValue().indexOf('=');
				for (final ValueTablePresentation element : tableAsArray) {
					for (int i = element.getRoundedValue().indexOf('='); i < equalsIndex; i++)
						element.setRoundedValue(element.getRoundedValue().substring(0, 2) + ' '
								+ element.getRoundedValue().substring(2));
					listModel.addElement(element);
				}

			}

		}
	}

}
