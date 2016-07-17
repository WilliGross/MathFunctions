package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.Function;
import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonLarge;

public class PanelFunctionActionsMenu_Menu extends RequestFocusForDefaultComponentPanel {

	private static final long serialVersionUID = 1L;
	private JLabel heading, function;
	private CustomButtonLarge b1_table, b2_point, b3_mirror;
	private JCheckBox saveInFile;
	
	public PanelFunctionActionsMenu_Menu(Function function) {
		setLayout(null);
		
		heading = new JLabel(Strings.getString("PanelFunctionActionsMenu_Menu.label_heading"), SwingConstants.CENTER); //$NON-NLS-1$
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 40, 400, 30);
		add(heading);
		
		this.function = new JLabel(function.toString(), SwingConstants.CENTER);
		this.function.setFont(FrameMain.getGlobalFont_Bold());
		this.function.setBounds(100, 70, 400, 30);
		add(this.function);
		
		b1_table = new CustomButtonLarge(Strings.getString("PanelFunctionActionsMenu_Menu.button_table")); //$NON-NLS-1$
		b1_table.setLocation(100, 110);
		b1_table.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel instance = FrameMain.getInstance().getPanelCenter();
				if (instance instanceof PanelFunctionActionsMenu)
					((PanelFunctionActionsMenu) instance).table();
			}
			
		});
		add(b1_table);
		
		b2_point = new CustomButtonLarge(Strings.getString("PanelFunctionActionsMenu_Menu.button_point")); //$NON-NLS-1$
		b2_point.setLocation(100, 145);
		b2_point.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel instance = FrameMain.getInstance().getPanelCenter();
				if (instance instanceof PanelFunctionActionsMenu)
					((PanelFunctionActionsMenu) instance).point();
			}
			
		});
		add(b2_point);
		
		b3_mirror = new CustomButtonLarge(Strings.getString("PanelFunctionActionsMenu_Menu.button_mirror")); //$NON-NLS-1$
		b3_mirror.setLocation(100, 180);
		b3_mirror.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel instance = FrameMain.getInstance().getPanelCenter();
				if (instance instanceof PanelFunctionActionsMenu)
					((PanelFunctionActionsMenu) instance).mirror();
			}
		});
		add(b3_mirror);
		
		saveInFile = new JCheckBox(Strings.getString("PanelFunctionActionsMenu_Menu.checkBox_save_message")); //$NON-NLS-1$
		saveInFile.setToolTipText(Strings.getString("PanelFunctionActionsMenu_Menu.checkBox_save_tooltip")); //$NON-NLS-1$
		saveInFile.setHorizontalAlignment(SwingConstants.CENTER);
		saveInFile.setBounds(100, 220, 400, 30);
		saveInFile.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (saveInFile.isSelected())
					FrameMain.getInstance().getMainLogic().saveFunctionInFile(function);
				else
					FrameMain.getInstance().getMainLogic().removeFunctionFromFile(function);
				FrameMain.getInstance().getMainLogic().displayFunctionsDat();
			}
		});
		add(saveInFile);
		
		setDefaultComponent(b1_table);
	}
}
