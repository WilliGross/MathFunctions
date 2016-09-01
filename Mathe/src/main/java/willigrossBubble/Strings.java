package willigrossBubble;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Strings {

	private static final String			BUNDLE_NAME		= "assets.lang.strings";				//$NON-NLS-1$

	private static final ResourceBundle	RESOURCE_BUNDLE	= ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getStringAsHTML(String key) {
		try {
			return Utility.convertToHTML(RESOURCE_BUNDLE.getString(key));
		} catch (@SuppressWarnings("unused") final MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (@SuppressWarnings("unused") final MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
