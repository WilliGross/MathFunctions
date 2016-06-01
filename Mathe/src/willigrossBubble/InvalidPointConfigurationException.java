package willigrossBubble;


public class InvalidPointConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;
	
    /**
     * Constructs an <code>IllegalArgumentException</code> with no
     * detail message.
     */
	public InvalidPointConfigurationException() {
		super();
	}
	
    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
	public InvalidPointConfigurationException(String s) {
		super(s);
	}
}
