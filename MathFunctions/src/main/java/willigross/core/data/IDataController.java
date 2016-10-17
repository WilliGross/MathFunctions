package willigross.core.data;

import willigross.core.logic.Function;

public interface IDataController {

	/**
	 * Save a function in the storage file
	 *
	 * @param function the function to save
	 */
	public void saveFunctionInFile(Function function);
	
	/**
	 * Removes a function from the storage file but not from the functions list (-> effective after restart)
	 *
	 * @param function the function to remove
	 */
	public void removeFunctionFromFile(Function function);

	/**
	 * Checks if a function is saved in the storage file
	 *
	 * @param function the function to check
	 * @return true if the function is stored, otherwise false
	 */
	public boolean isFunctionSaved(Function function);
	
	/**
	 * Loads all functions from the storage file
	 *
	 * @return the loaded functions as an array
	 */
	public Function[] loadFunctions();

}
