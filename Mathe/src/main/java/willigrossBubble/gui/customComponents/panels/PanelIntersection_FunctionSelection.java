package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import willigrossBubble.Function;
import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonSmall;

public class PanelIntersection_FunctionSelection extends CenterPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel heading, instruction;
	private DefaultListModel<Function> listModel;
	private JList<Function> result;
	private JScrollPane resultScrollPane;
	private CustomButtonSmall go;
	private Function function1, function2;
	
	public PanelIntersection_FunctionSelection() {
		
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelIntersection_FunctionSelection.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 40, 400, 30);
		add(heading);
		
		listModel = new DefaultListModel<>();
		for (Function f : FrameMain.getInstance().getMainLogic().getAllFunctions())
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
		result.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				function1 = result.getSelectedValue();
				try {
					function2 = result.getSelectedValuesList().get(1);
				} catch (@SuppressWarnings("unused") IndexOutOfBoundsException ex) {
					function2 = null;
				}
				if (function1 != null && function2 != null)
					go.setEnabled(true);
				else
					go.setEnabled(false);
			}
		});
		
		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 300);
		add(resultScrollPane);
		
		instruction = new JLabel(Strings.getStringAsHTML("PanelIntersection_FunctionSelection.label_instruction"), SwingConstants.CENTER);  //$NON-NLS-1$
		instruction.setBounds(100, 405, 400, 30);
		add(instruction);
		
		go = new CustomButtonSmall(Strings.getStringAsHTML("PanelIntersection_FunctionSelection.button_go")); //$NON-NLS-1$
		go.setLocation(250, 450);
		go.setEnabled(false);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					FrameMain.getInstance().panelIntersection(function1, function2);
			}
			
		});
		add(go);
		
	}
	
	
	@Override
	public void back() {
		FrameMain.getInstance().panelMain();
	}
	
}
