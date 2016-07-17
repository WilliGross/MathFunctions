package willigrossBubble;


public class InvalidPointConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;
	private String tooltip;
	
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
	
	
    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message and tooltip.
     *
     * @param   s   the detail message.
     */
	public InvalidPointConfigurationException(String s, String tooltip) {
		super(s);
		this.tooltip = tooltip;
	}

	
	/**
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}
	

	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	
	/**
	 * @return true if exception has a tooltip set
	 */
	public boolean hasTooltip() {
		if (tooltip != null && !tooltip.equals(""))
			return true;
		return false;
	}
	
	
}
