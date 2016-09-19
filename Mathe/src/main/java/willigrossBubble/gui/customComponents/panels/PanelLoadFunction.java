package willigrossBubble.gui.customComponents.panels;

import java.awt.Font;
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

	private static final long					serialVersionUID	= 1L;
	private final JLabel						heading;
	private final DefaultListModel<Function>	listModel;
	private final JList<Function>				result;
	private final JScrollPane					resultScrollPane;

	public PanelLoadFunction() {
		
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelLoadFunction.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);
		
		listModel = new DefaultListModel<>();
		for (final Function f : FrameMain.getInstance().getMainLogic().getAllFunctions())
			listModel.addElement(f);
		result = new JList<>(listModel);
		result.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result.setSelectionModel(new DefaultListSelectionModel());
		result.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!result.isSelectionEmpty())
					FrameMain.getInstance().panelFunctionActionsMenu(listModel.get(result.getSelectedIndex()),
							FrameMain.getInstance().getPanelCenter());
			}
		});
		result.setFont(FrameMain.getMonospacedFont().deriveFont(Font.BOLD, 14));
		
		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setBounds(100, 90, 400, 400);
		add(resultScrollPane);
	}

	@Override
	public void back() {
		FrameMain.getInstance().panelMain();
	}

}
