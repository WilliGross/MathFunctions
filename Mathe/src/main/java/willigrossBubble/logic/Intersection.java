package willigrossBubble.logic;

import willigrossBubble.data.Strings;

public class Intersection extends Point {
	
	private static final long	serialVersionUID	= 1L;
	/** the two functions used to calculate the intersection point's coordinates */
	private Function			f, g;
	
	/**
	 * the intersection point's constructor
	 * 
	 * @throws IntersectionNotFoundException
	 */
	public Intersection(Function f, Function g) throws IntersectionNotFoundException {
		super(0, 0);
		this.f = f;
		this.g = g;
		update();
	}
	
	/**
	 * Get the interaction point's first function
	 * 
	 * @return function f
	 */
	public Function getF() {
		return f;
	}
	
	/**
	 * Get the interaction point's second function
	 * 
	 * @return function g
	 */
	public Function getG() {
		return g;
	}
	
	/**
	 * Set the intersection point's first function
	 * 
	 * @param f
	 *            the function to set
	 * @throws IntersectionNotFoundException
	 */
	public void setF(Function f) throws IntersectionNotFoundException {
		this.f = f;
		update();
	}
	
	/**
	 * Set the intersection point's second function
	 * 
	 * @param g
	 *            the function to set
	 * @throws IntersectionNotFoundException
	 */
	public void setG(Function g) throws IntersectionNotFoundException {
		this.g = g;
		update();
	}
	
	private void update() throws IntersectionNotFoundException {
		if (!f.getExpression().contains("x") && !g.getExpression().contains("x")) //$NON-NLS-1$//$NON-NLS-2$
			calcNoX();
		else
			calcBothX();
	}
	
	/** 'calculates' the intersection of two constants */
	private void calcNoX() {
		if (f.evaluate(0) == g.evaluate(0)) {
			setX(0);
			setY(f.evaluate(0));
		}
	}
	
	/**
	 * calculates the intersection
	 * 
	 * @throws IntersectionNotFoundException
	 */
	private void calcBothX() throws IntersectionNotFoundException {

		if (f.equals(g)) {
			setX(Double.POSITIVE_INFINITY);
			setY(Double.POSITIVE_INFINITY);
			return;
		}

		//variables used in calculation
		boolean found = false;
		boolean tryIncreasingX = true;
		final int dirChanges = 1;
		int tries = 0;
		double yF = 0, yG = 0, currX, absDiff = 0, prevAbsDiff;
		
		currX = Math.floor(Math.random() * 10);
		
		while (!found) {
			tries++;
			prevAbsDiff = absDiff;
			
			//calculate new values
			yF = f.evaluate(currX);
			yG = g.evaluate(currX);
			absDiff = Math.abs(yF - yG);
			
			if ((absDiff <= 0.00005) && (absDiff >= -0.00005)) { //correct x value found?
				found = true;
				setX(currX);
			} else { // not found yet :(
				
				//change checking direction?
				if (prevAbsDiff <= absDiff)
					if (tryIncreasingX == true)
						tryIncreasingX = false;
					else
						tryIncreasingX = true;
					
				//set next x value
				if (tryIncreasingX)
					currX += absDiff / (10 * dirChanges);
				else
					currX -= absDiff / (10 * dirChanges);
				
			}

			if ((dirChanges > 10000) || (tries > 20000)) {
				System.out.println("Can't find intersection!"); //$NON-NLS-1$
				setX(Double.NaN);
				setY(Double.NaN);
				throw new IntersectionNotFoundException(Strings.getString("Intersection.intersectionNotFound")); //$NON-NLS-1$
			}
			
		} //end of while loop
		
		prevAbsDiff = absDiff;
		
		currX = Math.round(currX);
		
		yF = f.evaluate(currX);
		yG = g.evaluate(currX);
		absDiff = Math.abs(yF - yG);
		
		if (absDiff == 0)
			setX(currX);
		
		//calculate corresponding y - value
		setY(f.evaluate(getX()));
	}
	
	/** string representation of the intersection point */
	@Override
	public String toString() {
		return Strings.getString("Intersection.toString_prefix") + super.toString(); //$NON-NLS-1$
	}
	
}