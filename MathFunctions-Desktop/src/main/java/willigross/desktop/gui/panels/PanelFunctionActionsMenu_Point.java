package willigross.desktop.gui.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.data.UtilityData;
import willigross.core.logic.Function;
import willigross.core.logic.Point;
import willigross.core.logic.Validations;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.FocusAdapter_SelectAll;
import willigross.desktop.gui.FrameMain;

public class PanelFunctionActionsMenu_Point extends RequestFocusForDefaultComponentPanel {

	private static final long	serialVersionUID	= 1L;

	private static final Logger	logger				= LoggerFactory.getLogger(PanelFunctionActionsMenu_Point.class);

	private final JTextField	p1x, p1y;
	private final JLabel		p1, result, heading;
	private final Function		function;
	private Image				symbol;

	public PanelFunctionActionsMenu_Point(Function f) {

		logger.info("Initializing new PanelFunctionActionsMenu_Point"); //$NON-NLS-1$

		function = f;

		setLayout(null);

		final FocusAdapter_SelectAll focusAdapter_SelectAll = new FocusAdapter_SelectAll();

		heading = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_heading"), //$NON-NLS-1$
				SwingConstants.CENTER);
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 0, 400, 50);
		add(heading);

		final KeyAdapter keyListener = new KeyListeneR();

		p1 = new JLabel(Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_point"), SwingConstants.RIGHT); //$NON-NLS-1$
		p1.setBounds(40, 55, 55, 30);
		add(p1);

		p1x = new JTextField(Strings.getString("PanelFunctionActionsMenu_Point.textFieldPlaceholder_x-coordinate")); //$NON-NLS-1$
		p1x.setBounds(100, 55, 190, 30);
		p1x.addFocusListener(focusAdapter_SelectAll);
		p1x.addKeyListener(keyListener);
		add(p1x);

		p1y = new JTextField(Strings.getString("PanelFunctionActionsMenu_Point.textFieldPlaceholder_y-coordinate")); //$NON-NLS-1$
		p1y.setBounds(310, 55, 190, 30);
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

					final double px = UtilityData.readDoubleFromStringInput(p1x.getText()),
							py = UtilityData.readDoubleFromStringInput(p1y.getText());
					final Point p = new Point(px, py);

					logger.info("Updating result and image"); //$NON-NLS-1$
					
					if (function.testPointOnGraph(p)) {
						result.setText(Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_result_onGraph")); //$NON-NLS-1$

						try {
							symbol = ImageIO
									.read(getClass().getClassLoader()
											.getResourceAsStream("willigross/desktop/assets/images/greenCheckMark.png")) //$NON-NLS-1$
									.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
							repaint();
						} catch (final IOException ex) {
							logger.error("Caught {} when trying to read image: ", ex.getClass().getName(), ex); //$NON-NLS-1$
						}

					} else {
						result.setText(
								Strings.getStringAsHTML("PanelFunctionActionsMenu_Point.label_result_notOnGraph")); //$NON-NLS-1$

						try {
							symbol = ImageIO
									.read(getClass().getClassLoader()
											.getResourceAsStream("willigross/desktop/assets/images/redX.png")) //$NON-NLS-1$
									.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
							repaint();
						} catch (final IOException ex) {
							logger.error("Caught {} when trying to read image: ", ex.getClass().getName(), ex); //$NON-NLS-1$
						}
					}
				}
			}
		}
	}
}
