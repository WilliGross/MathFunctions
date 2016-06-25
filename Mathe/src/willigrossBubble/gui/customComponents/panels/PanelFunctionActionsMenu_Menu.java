package willigrossBubble.gui.customComponents.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import willigrossBubble.Function;
import willigrossBubble.gui.FrameMain;
import willigrossBubble.gui.customComponents.buttons.CustomButtonLarge;

public class PanelFunctionActionsMenu_Menu extends RequestFocusForDefaultComponentPanel {

	private static final long serialVersionUID = 1L;
	private JLabel desc, function;
	private CustomButtonLarge b1_table, b2_point, b3_mirror;
	private JCheckBox saveInFile;
	
	public PanelFunctionActionsMenu_Menu(Function function) {
		setLayout(null);
		
		desc = new JLabel("What would you like to do with your function?", SwingConstants.CENTER);
		desc.setFont(FrameMain.getGlobalFont());
		desc.setBounds(100, 40, 400, 30);
		add(desc);
		
		this.function = new JLabel(function.toString(), SwingConstants.CENTER);
		this.function.setFont(FrameMain.getGlobalFont_Bold());
		this.function.setBounds(100, 70, 400, 30);
		add(this.function);
		
		b1_table = new CustomButtonLarge("Calculate value table");
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
		
		b2_point = new CustomButtonLarge("Check if a specified point lies on the function's graph");
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
		
		b3_mirror = new CustomButtonLarge("Create a mirrored version of the function");
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
		
		saveInFile = new JCheckBox("Save this function in 'Functions.dat'?");
		saveInFile.setToolTipText("Check this to keep your function after a restart of the program");
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
