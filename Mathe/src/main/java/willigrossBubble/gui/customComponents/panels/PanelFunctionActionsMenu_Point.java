package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.Function;
import willigrossBubble.Point;
import willigrossBubble.Strings;
import willigrossBubble.Utility;
import willigrossBubble.Validations;
import willigrossBubble.gui.FocusAdapter_SelectAll;
import willigrossBubble.gui.FrameMain;

public class PanelFunctionActionsMenu_Point extends RequestFocusForDefaultComponentPanel {

	private static final long	serialVersionUID	= 1L;
	private final JTextField	p1x, p1y;
	private final JLabel		p1, result, heading;
	private final Function		function;
	private Image				symbol;

	public PanelFunctionActionsMenu_Point(Function f) {

		function = f;
		
		setLayout(null);

		final FocusAdapter_SelectAll focusAdapter_SelectAll = new FocusAdapter_SelectAll();

		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 0, 400, 30);
		add(heading);

		final KeyAdapter keyListener = new KeyListeneR();

		p1 = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_point"), SwingConstants.RIGHT); //$NON-NLS-1$
		p1.setBounds(40, 40, 50, 30);
		add(p1);

		p1x = new JTextField(Strings.getString("PanelFunctionActionsMenu_Point.textFieldPlaceholder_x-coordinate")); //$NON-NLS-1$
		p1x.setBounds(100, 40, 190, 30);
		p1x.addFocusListener(focusAdapter_SelectAll);
		p1x.addKeyListener(keyListener);
		add(p1x);

		p1y = new JTextField(Strings.getString("PanelFunctionActionsMenu_Point.textFieldPlaceholder_y-coordinate")); //$NON-NLS-1$
		p1y.setBounds(310, 40, 190, 30);
		p1y.addFocusListener(focusAdapter_SelectAll);
		p1y.addKeyListener(keyListener);
		add(p1y);

		result = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
		result.setBounds(100, 120, 400, 30);
		add(result);

		setDefaultComponent(p1x);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(symbol, 275, 150, null);
	}
	
	private class KeyListeneR extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e) {

			result.setText(""); //$NON-NLS-1$
			symbol = null;
			repaint();

			final JTextField source = (JTextField) e.getSource();
			if (!Validations.canConvertToNumber(source.getText()))
				source.setBorder(new LineBorder(Color.RED, 2));
			else {
				source.setBorder(new LineBorder(Color.GRAY));
				if (Validations.canConvertToNumber(p1x.getText()) && Validations.canConvertToNumber(p1y.getText())) {

					final double px = Utility.readDoubleFromStringInput(p1x.getText()),
							py = Utility.readDoubleFromStringInput(p1y.getText());
					final Point p = new Point(px, py);

					if (function.testPointOnGraph(p)) {
						result.setText(Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_result_onGraph")); //$NON-NLS-1$

						try {
							symbol = ImageIO
									.read(new File(getClass().getClassLoader()
											.getResource("assets/images/greenCheckMark.png").getFile())) //$NON-NLS-1$
									.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
							repaint();
						} catch (final IOException ex) {
							ex.printStackTrace();
						}

					} else {
						result.setText(
								Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_result_notOnGraph")); //$NON-NLS-1$

						try {
							symbol = ImageIO.read(new File(
									getClass().getClassLoader().getResource("assets/images/redX.png").getFile())) //$NON-NLS-1$
									.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
							repaint();
						} catch (final IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
}
