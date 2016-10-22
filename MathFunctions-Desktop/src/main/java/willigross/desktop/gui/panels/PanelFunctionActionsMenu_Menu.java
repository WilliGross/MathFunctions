package willigross.desktop.gui.panels;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.Controller;
import willigross.core.logic.Function;
import willigross.desktop.data.MainData;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.FrameMain;
import willigross.desktop.gui.buttons.CustomButtonLarge;

public class PanelFunctionActionsMenu_Menu extends RequestFocusForDefaultComponentPanel {
	
	private static final long		serialVersionUID	= 1L;
	
	private static final Logger		logger				= LoggerFactory.getLogger(PanelFunctionActionsMenu_Menu.class);
	
	private final JLabel			heading, function;
	private final CustomButtonLarge	b1_table, b2_point, b3_mirror;
	private final JCheckBox			saveInFile;
	
	public PanelFunctionActionsMenu_Menu(Function function) {
		
		logger.info("Initializing new PanelFunctionActionsMenu_Menu"); //$NON-NLS-1$
		
		setLayout(null);
		
		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Menu.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);
		
		this.function = new JLabel(function.toString(), SwingConstants.CENTER);
		this.function.setFont(FrameMain.getHeadingFont().deriveFont(Font.BOLD));
		this.function.setBounds(100, 70, 400, 30);
		add(this.function);
		
		b1_table = new CustomButtonLarge(Strings.getStringAsHTML("PanelFunctionActionsMenu_Menu.button_table")); //$NON-NLS-1$
		b1_table.setLocation(100, 110);
		b1_table.addActionListener(e -> {
			final CenterPanel instance = FrameMain.getInstance().getPanelCenter();
			if (instance instanceof PanelFunctionActionsMenu)
				((PanelFunctionActionsMenu) instance).table();
		});
		add(b1_table);
		
		b2_point = new CustomButtonLarge(Strings.getStringAsHTML("PanelFunctionActionsMenu_Menu.button_point")); //$NON-NLS-1$
		b2_point.setLocation(100, 145);
		b2_point.addActionListener(e -> {
			final CenterPanel instance = FrameMain.getInstance().getPanelCenter();
			if (instance instanceof PanelFunctionActionsMenu)
				((PanelFunctionActionsMenu) instance).point();
		});
		add(b2_point);
		
		b3_mirror = new CustomButtonLarge(Strings.getStringAsHTML("PanelFunctionActionsMenu_Menu.button_mirror")); //$NON-NLS-1$
		b3_mirror.setLocation(100, 180);
		b3_mirror.addActionListener(e -> {
			final CenterPanel instance = FrameMain.getInstance().getPanelCenter();
			if (instance instanceof PanelFunctionActionsMenu)
				((PanelFunctionActionsMenu) instance).mirror();
		});
		add(b3_mirror);
		
		saveInFile = new JCheckBox(Strings.getStringAsHTML("PanelFunctionActionsMenu_Menu.checkBox_save_message")); //$NON-NLS-1$
		saveInFile.setToolTipText(Strings.getStringAsHTML("PanelFunctionActionsMenu_Menu.checkBox_save_tooltip")); //$NON-NLS-1$
		saveInFile.setHorizontalAlignment(SwingConstants.CENTER);
		saveInFile.setBounds(100, 220, 400, 30);
		if (Controller.getInstance().getDataController().isFunctionSaved(function))
			saveInFile.setSelected(true);
		saveInFile.addItemListener(e -> {
			if (saveInFile.isSelected())
				Controller.getInstance().getDataController().saveFunctionInFile(function);
			else
				Controller.getInstance().getDataController().removeFunctionFromFile(function);
			if (Controller.getInstance().getDataController() instanceof MainData)
				logger.info("Functions saved in storage file: " //$NON-NLS-1$
						+ ((MainData) Controller.getInstance().getDataController()).displayStorageFile());
		});
		add(saveInFile);
		
		setDefaultComponent(b1_table);
	}
}
