package willigrossBubble;

import java.util.HashMap;

public class MainLogic {
	
	/**A utility array with the names of functions, f to z then F to Z */
	public static char[] names = {'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	/**A list where all functions are stored */
	private HashMap<Character, Function> functions = new HashMap<>();
	
	/**
	 * Stores a function in the HasMap
	 * @param function the function to store
	 * @return the function's name as a Character object
	 */
	public Character storeFunction(Function function) {
		if (functions.containsValue(function)) 
			return null;
		char name = getNextName();
		functions.put(name, function);
		return name;
	}
	
	public char getNextName() {
		return names[functions.size()];
	}
}
