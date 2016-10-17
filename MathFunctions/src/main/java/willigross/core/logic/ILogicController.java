package willigross.core.logic;

/**
 * This interface defines all methods a ILogicController has to have. The recommended implementation is in
 * MainLogic.java
 *
 * @see willigross.core.logic.MainLogic
 */
public interface ILogicController {

	/**
	 * Stores a function
	 *
	 * @param function the function to store
	 * @return the function's name as a Character object
	 */
	public Character storeFunction(Function function);
	
	/**
	 * Stores all functions from a <code>Function[]</code> array
	 *
	 * @param functions the array of functions to store
	 */
	public void storeAllFunctions(Function[] functions);
	
	/**
	 * Get the function for key <code>name</code>
	 *
	 * @param name the key
	 * @return the function
	 */
	public Function getFunction(char name);
	
	/**
	 * Get all functions
	 *
	 * @return all functions as an array
	 */
	public Function[] getAllFunctions();
	
	/**
	 * Get the function added last
	 *
	 * @return the requested function
	 */
	public Function getLatestFunction();

	/**
	 * Get the name of a function
	 *
	 * @param function the function to find the name for
	 * @return the name as a <code>Character</code>
	 */
	public char getName(Function function);
	
	/**
	 * Get the next function's name
	 *
	 * @return the new name
	 */
	public char getNextName();
	
}
