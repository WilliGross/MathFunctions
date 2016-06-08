package willigrossBubble;

import com.google.common.collect.HashBiMap;

public class MainLogic {
	
	/**A utility array with the names of functions, f to z then F to Z */
	private static char[] names = {'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	/**A list where all functions are stored */
	private HashBiMap<Character, Function> functions = HashBiMap.create(names.length);
	
	/**
	 * Stores a function in the HasMap
	 * @param function the function to store
	 * @return the function's name as a Character object
	 */
	public Character storeFunction(Function function) {
		if (functions.containsValue(function)) 
			return null;
		char name = getNextName();
		function.setName(name);
		functions.put(name, function);
		System.out.println(functions.get(names[functions.size() -1]));
		return name;
	}
	
	/**
	 * Get the next function's name
	 * @return the new name
	 */
	public char getNextName() {
		return names[functions.size()];
	}
	
	/**
	 * Get the function for key name
	 * @param name the key
	 * @return the function
	 */
	public Function getFunction(char name) {
		return functions.get(name);
	}
	
	/**
	 * Get the name of a function
	 * @param function the function to find the name for
	 * @return the name as a Character
	 */
	public char getName(Function function) {
		return functions.inverse().get(function);
	}
	
	/**
	 * Get the latest function
	 * @return the requested function
	 */
	public Function getLatestFunction() {
		return functions.get(names[functions.size()]);
	}
	
	
}
