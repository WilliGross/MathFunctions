package willigrossBubble.gui;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;


public class FocusAdapter_SelectAll extends FocusAdapter {

	/**If source is a instance of JTextField select text when focus gained*/
	@Override
	public void focusGained(FocusEvent e) {
		Component source = e.getComponent();
		if (source instanceof JTextField)
			((JTextField) source).selectAll();
	}
	
}
