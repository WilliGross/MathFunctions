package willigrossBubble.core.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigrossBubble.data.Strings;

public class Intersection extends Point {
	
	private static final long	serialVersionUID	= 1L;
	
	private static final Logger	logger				= LoggerFactory.getLogger(Intersection.class);
	
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
		logger.info("Creating new intersection with {} and {}", f, g); //$NON-NLS-1$
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
	 * @param f the function to set
	 * @throws IntersectionNotFoundException
	 */
	public void setF(Function f) throws IntersectionNotFoundException {
		this.f = f;
		logger.info("Changed function f: {}", f); //$NON-NLS-1$
		update();
	}
	
	/**
	 * Set the intersection point's second function
	 *
	 * @param g the function to set
	 * @throws IntersectionNotFoundException
	 */
	public void setG(Function g) throws IntersectionNotFoundException {
		this.g = g;
		logger.info("Changed function g: {}", g); //$NON-NLS-1$
		update();
	}
	
	private void update() throws IntersectionNotFoundException {

		if (f.equals(g)) {
			logger.info("There is an infinite number of intersections since both functions have the same expression"); //$NON-NLS-1$
			setX(Double.POSITIVE_INFINITY);
			setY(Double.POSITIVE_INFINITY);
			return;
		}

		logger.info("Deciding wether to use full algorithm"); //$NON-NLS-1$
		if (!f.getExpression().contains("x") && !g.getExpression().contains("x")) //$NON-NLS-1$//$NON-NLS-2$
			calcNoX();
		else
			calcBothX();
	}
	
	/** 'calculates' the intersection of two constants */
	private void calcNoX() {
		logger.info("Using simple comparison as both functions are constants"); //$NON-NLS-1$
		if (f.evaluate(0) == g.evaluate(0)) {
			setX(0);
			setY(f.evaluate(0));
			logger.info("Intersection found at {}", toString()); //$NON-NLS-1$
		}
		logger.info("No intersection found"); //$NON-NLS-1$
	}
	
	/**
	 * calculates the intersection
	 *
	 * @throws IntersectionNotFoundException
	 */
	private void calcBothX() throws IntersectionNotFoundException {
		logger.info("Calculating Intersection using Newton's method"); //$NON-NLS-1$

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
				setX(Double.NaN);
				setY(Double.NaN);
				logger.info("There doesn't seem to be an intersection between {} and {}", f, g); //$NON-NLS-1$
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

		logger.info("Intersection found at {}", toString()); //$NON-NLS-1$
	}
	
	/** string representation of the intersection point */
	@Override
	public String toString() {
		return Strings.getString("Intersection.toString_prefix") + super.toString(); //$NON-NLS-1$
	}
	
}
