package willigross.core.logic;

/**
 * Representation of a row in an value table. Objects of this class store a unrounded value and a rouded value. It is
 * used by Function.table()
 */
public class ValueTablePresentation {
	
	private String	unroundedValue;
	private String	roundedValue;

	public ValueTablePresentation(String unroundedValue, String roundedValue) {
		this.unroundedValue = unroundedValue;
		this.roundedValue = roundedValue;
	}
	
	public ValueTablePresentation(String value) {
		unroundedValue = value;
		roundedValue = value;
	}

	/**
	 * @return the unroundedValue
	 */
	public String getUnroundedValue() {
		return unroundedValue;
	}

	/**
	 * @return the roundedValue
	 */
	public String getRoundedValue() {
		return roundedValue;
	}

	/**
	 * @param unroundedValue the unroundedValue to set
	 */
	public void setUnroundedValue(String unroundedValue) {
		this.unroundedValue = unroundedValue;
	}

	/**
	 * @param roundedValue the roundedValue to set
	 */
	public void setRoundedValue(String roundedValue) {
		this.roundedValue = roundedValue;
	}

	@Override
	public int hashCode() {
		return getUnroundedValue().hashCode() + getRoundedValue().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Function))
			return false;
		if (((ValueTablePresentation) obj).getRoundedValue().equals(getRoundedValue())
				&& ((ValueTablePresentation) obj).getUnroundedValue().equals(getUnroundedValue()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return roundedValue;
	}
}
