package willigrossBubble.gui.customComponents.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import willigrossBubble.Function;
import willigrossBubble.Intersection;
import willigrossBubble.IntersectionNotFoundException;
import willigrossBubble.Strings;
import willigrossBubble.gui.FrameMain;

public class PanelIntersection extends CenterPanel {

	private static final long	serialVersionUID	= 1L;
	private final JLabel		heading, intersection, intersection_desc;
	private Intersection		intersectionPoint;
	
	public PanelIntersection(Function function1, Function function2) {

		setLayout(null);

		String headingRaw = Strings.getStringAsHTML("PanelIntersection.label_heading"); //$NON-NLS-1$
		headingRaw = headingRaw.replace("$FUNCTION1$", function1.toString()); //$NON-NLS-1$
		headingRaw = headingRaw.replace("$FUNCTION2$", function2.toString()); //$NON-NLS-1$
		heading = new JLabel(headingRaw, SwingConstants.CENTER);
		heading.setFont(FrameMain.getGlobalFont());
		heading.setBounds(100, 40, 400, 50);
		add(heading);
		
		intersection_desc = new JLabel(Strings.getStringAsHTML("PanelIntersection.label_intersection_desc"), //$NON-NLS-1$
				SwingConstants.CENTER);
		intersection_desc.setFont(FrameMain.getGlobalFont());
		intersection_desc.setBounds(100, 150, 400, 30);
		add(intersection_desc);

		intersection = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
		try {
			intersectionPoint = new Intersection(function1, function2);
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
