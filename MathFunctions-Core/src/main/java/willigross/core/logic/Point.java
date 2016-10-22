package willigross.core.logic;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Point implements Serializable {
	
	private static final long	serialVersionUID	= 1L;

	private static final Logger	logger				= LoggerFactory.getLogger(Point.class);
	
	/**
	 * The x and y coordinate of the point
	 */
	private double				x, y;

	/**
	 * Get the point's x coordinate
	 *
	 * @return the point's x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the point's x coordinate
	 *
	 * @return the point's y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Set the point's x coordinate
	 *
	 * @param x - the x coordinate to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the point's y coordinate
	 *
	 * @param y - the y coordinate to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * A point's constructor
	 *
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		logger.info("New point P{} created", toString()); //$NON-NLS-1$
	}

	/**
	 * Checks in which quadrant the point is in: top right (1), top left (2), bottom left (3), bottom right (4) Number
	 * is (5) if the point lies on the y - axis, (6) if it lies on the x-axis and (7) if it's on both
	 *
	 * @return the quadrant
	 */
	public int getQuadrant() {
		if (x == 0) {
			if (y == 0)
				return 7; //both
			return 5; //y-axis only
		}
		if (y == 0)
			return 6; //x-axis only
		if (y > 0) {
			if (x > 0)
				return 1; //top right
			return 2; //top left
		}
		if (x < 0)
			return 3; //bottom left
		return 4; //bottom right
	}

	/**
	 * A string to represent the point
	 */
	@Override
	public String toString() {
		return "(" + UtilityLogic.roundDouble(getX(), 3) + "|" + UtilityLogic.roundDouble(getY(), 3) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * Override .equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if ((x == ((Point) obj).getX()) && (y == ((Point) obj).getY()))
			return true;
		return false;
	}

	/**
	 * Override .hashCode()
	 */
	@Override
	public int hashCode() {
		return (new Double(x + y)).hashCode();
	}
	
}
