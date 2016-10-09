package willigrossBubble.gui.customComponents.panels;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import willigrossBubble.core.Controller;
import willigrossBubble.core.logic.Function;
import willigrossBubble.data.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;

public class PanelIntersection_FunctionSelection extends CenterPanel {

	private static final long					serialVersionUID	= 1L;
	private final JLabel						heading, instruction;
	private final DefaultListModel<Function>	listModel;
	private final JList<Function>				result;
	private final JScrollPane					resultScrollPane;
	private final CustomButtonSmall				go;
	private Function							function1, function2;

	public PanelIntersection_FunctionSelection() {

		setLayout(null);

		heading = new JLabel(Strings.getStringAsHTML("PanelIntersection_FunctionSelection.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);
		
		go = new CustomButtonSmall(Strings.getStringAsHTML("PanelIntersection_FunctionSelection.button_go")); //$NON-NLS-1$
		go.setLocation(250, 450);
		go.setEnabled(false);
		go.addActionListener(e -> FrameMain.getInstance().panelIntersection(function1, function2));
		add(go);

		listModel = new DefaultListModel<>();
		for (final Function f : Controller.getInstance().getLogicController().getAllFunctions())
			listModel.addElement(f);
		result = new JList<>(listModel);
		result.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result.setSelectionModel(new DefaultListSelectionModel() {

			private static final long serialVersionUID = 1L;
			
			//			@Override
			//			public void setSelectionInterval(int index0, int index1) {
			//				if (isSelectedIndex(index0))
			//					removeSelectionInterval(index0, index1);
			//				else
			//					addSelectionInterval(index0, index1);
			//				fireValueChanged(index0, index1);
			//			}

		});
		result.addListSelectionListener(e -> {
			function1 = result.getSelectedValue();
			try {
				function2 = result.getSelectedValuesList().get(1);
			} catch (@SuppressWarnings("unused") final IndexOutOfBoundsException ex) {
				function2 = null;
			}
			if ((function1 != null) && (function2 != null))
				go.setEnabled(true);
			else
				go.setEnabled(false);
		});

		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 300);
		add(resultScrollPane);

		instruction = new JLabel(Strings.getStringAsHTML("PanelIntersection_FunctionSelection.label_instruction"), //$NON-NLS-1$
				SwingConstants.CENTER);
		instruction.setBounds(100, 405, 400, 30);
		add(instruction);
	}
	
	@Override
	public void back() {
		FrameMain.getInstance().panelMain();
	}

}
