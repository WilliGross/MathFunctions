package willigrossBubble;

public class Intersection extends Point {

	private static final long serialVersionUID = 1L;
	/** the two functions used to calculate the intersection point's coordinates */
	private Function f, g;

	/** the intersection point's constructor */
	public Intersection(Function f, Function g) {
		super(0, 0);
		this.f = f;
		this.g = g;
		calc();
	}

	/**
	 * Get the interaction point's first function
	 * @return function f
	 */
	public Function getF() {
		return f;
	}

	/**
	 * Get the interaction point's second function
	 * @return function g
	 */
	public Function getG() {
		return g;
	}

	/**
	 * Set the intersection point's first function
	 * @param f the function to set
	 */
	public void setF(Function f) {
		this.f = f;
		calc();
	}

	/**
	 * Set the intersection point's second function
	 * @param g the function to set
	 */
	public void setG(Function g) {
		this.g = g;
		calc();
	}


	/** calculates the intersection */
	private void calc() {
		
		if (f.equals(g)) {
			this.setX(Double.POSITIVE_INFINITY);
			this.setY(Double.POSITIVE_INFINITY);
			return;
		}
		
		//variables used in calculation
		boolean found = false;
		boolean tryIncreasingX = true;
		int dirChanges = 1, tries = 0;
		double yF = 0, yG = 0, currX, absDiff = 0, prevAbsDiff;

		currX = Math.floor(Math.random() * 10);

		while (!found) {
			tries++;
			prevAbsDiff = absDiff;

			//calculate new values
			yF = f.evaluate(currX);
			yG = g.evaluate(currX);
			absDiff = Math.abs(yF - yG);

			if (absDiff <= 0.00005 && absDiff >= -0.00005) { //correct x value found?
				found = true;
				this.setX(currX);
			} else { // not found yet :(

				//change checking direction?
				if (prevAbsDiff <= absDiff) {
					if (tryIncreasingX == true)
						tryIncreasingX = false;
					else
						tryIncreasingX = true;
				}

				//set next x value
				if (tryIncreasingX) {
					currX += absDiff / (10 * dirChanges);
				} else {
					currX -= absDiff / (10 * dirChanges);
				}

			}
			
			if (dirChanges > 30 || tries > 50) {
				this.setX(Double.NaN);
				this.setY(Double.NaN);
				return;
			}

		}//end of while loop

		prevAbsDiff = absDiff;

		currX = Math.round(currX);

		yF = f.evaluate(currX);
		yG = g.evaluate(currX);
		absDiff = Math.abs(yF - yG);

		if (absDiff == 0) {
			this.setX(currX);
		}

		//calculate corresponding y - value
		this.setY(f.evaluate(this.getX()));

	}

	/** string representation of the intersection point */
	@Override
	public String toString() {
		return Strings.getString("Intersection.toString_prefix") + super.toString(); //$NON-NLS-1$
	}

}
