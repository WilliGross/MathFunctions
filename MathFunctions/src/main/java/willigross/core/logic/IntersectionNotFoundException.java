package willigross.core.logic;

public class IntersectionNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs an <code>IntersectionNotFoundException</code> with no detail message.
	 */
	public IntersectionNotFoundException() {
		super();
	}
	
	/**
	 * Constructs an <code>IntersectionNotFoundException</code> with the specified detail message.
	 *
	 * @param s
	 *            the detail message.
	 */
	public IntersectionNotFoundException(String s) {
		super(s);
	}

}
