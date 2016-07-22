package willigrossBubble.gui.customComponents.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import willigrossBubble.Function;
import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;

public class PanelLoadFunction extends CenterPanel {

	private static final long serialVersionUID = 1L;
	private JLabel heading;
	private DefaultListModel<Function> listModel;
	private JList<Function> result;
	private JScrollPane resultScrollPane;

	public PanelLoadFunction() {
		
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelLoadFunction.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 40, 400, 30);
		add(heading);
		
		listModel = new DefaultListModel<>();
		for (Function f : FrameMain.getInstance().getMainLogic().getAllFunctions())
			listModel.addElement(f);
		result = new JList<>(listModel);
		result.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result.setSelectionModel(new DefaultListSelectionModel());
		result.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				FrameMain.getInstance().panelFunctionActionsMenu(listModel.get(result.getSelectedIndex()), FrameMain.getInstance().getPanelCenter());
			}
		});
		
		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 400);
		add(resultScrollPane);
	}

	@Override
	public void back() {
		FrameMain.getInstance().panelMain();
	}

}
