package willigross.desktop.gui.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.logic.Function;
import willigross.core.logic.Intersection;
import willigross.core.logic.IntersectionNotFoundException;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.FrameMain;

public class PanelIntersection extends CenterPanel {
	
	private static final long	serialVersionUID	= 1L;
	
	private static final Logger	logger				= LoggerFactory.getLogger(PanelIntersection.class);
	
	private final JLabel		heading, intersection, intersection_desc;
	private Intersection		intersectionPoint;

	public PanelIntersection(Function function1, Function function2) {
		
		logger.info("Initializing new PanelIntersection"); //$NON-NLS-1$

		setLayout(null);
		
		String headingRaw = Strings.getStringAsHTML("PanelIntersection.label_heading"); //$NON-NLS-1$
		headingRaw = headingRaw.replace("$FUNCTION1$", function1.toString()); //$NON-NLS-1$
		headingRaw = headingRaw.replace("$FUNCTION2$", function2.toString()); //$NON-NLS-1$
		heading = new JLabel(headingRaw, SwingConstants.CENTER);
		heading.setFont(FrameMain.getHeadingFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);

		intersection_desc = new JLabel(Strings.getStringAsHTML("PanelIntersection.label_intersection_desc"), //$NON-NLS-1$
				SwingConstants.CENTER);
		intersection_desc.setFont(FrameMain.getHeadingFont());
		intersection_desc.setBounds(100, 150, 400, 30);
		add(intersection_desc);
		
		intersection = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
		try {
			logger.info("Comissioning calculation of ointersection of {} and {}...", function1, function2); //$NON-NLS-1$
			intersectionPoint = new Intersection(function1, function2);
			logger.info("...{}", intersectionPoint); //$NON-NLS-1$
			intersection.setText(intersectionPoint.toString());
		} catch (final IntersectionNotFoundException e) {
			intersection.setText(e.getMessage());
		}
		intersection.setBounds(150, 190, 300, 30);
		intersection.setBackground(Color.WHITE);
		intersection.setOpaque(true);
		intersection.setBorder(new LineBorder(Color.GRAY));
		intersection.setFont(new Font(intersection.getFont().getName(), intersection.getFont().getStyle(), 15));
		add(intersection);
	}

	@Override
	public void back() {
		FrameMain.getInstance().panelIntersection_FunctionSelection();
	}
	
}
