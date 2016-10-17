package willigross.gui.customComponents.panels;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class RequestFocusForDefaultComponentPanel extends JPanel {
	
	private static final long	serialVersionUID	= 1L;
	private JComponent			defaultComponent;

	public RequestFocusForDefaultComponentPanel() {
		addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if (defaultComponent != null)
					defaultComponent.requestFocusInWindow();
			}
		});
	}
	
	/**
	 * @return the defaultComponent
	 */
	public JComponent getDefaultComponent() {
		return defaultComponent;
	}
	
	/**
	 * @param defaultComponent
	 *            the defaultComponent to set
	 */
	public void setDefaultComponent(JComponent defaultComponent) {
		this.defaultComponent = defaultComponent;
	}
}
