package willigrossBubble.logic;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.google.common.collect.HashBiMap;

import willigrossBubble.data.MainData;

public class MainLogic {
	
	//TODO rely on functions own name when saving?
	/** A utility array with the names of functions, f to z then F to Z */
	public static char[]							names		= { 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	/** Main data controller object */
	private final MainData							mainData;

	/** A list where all functions are stored */
	private final HashBiMap<Character, Function>	functions	= HashBiMap.create(names.length);
	
	/**
	 * Constructor of MainLogic that loads functions from file on call
	 */
	public MainLogic() {
		mainData = new MainData();
		functions.putAll(mainData.loadFunctions());
		displayFunctions();
	}

	/**
	 * @return main data controller
	 */
	public MainData getMainData() {
		return mainData;
	}
	
	/**
	 * @return the names
	 */
	public static char[] getNames() {
		return names;
	}

	/**
	 * Stores a function in the HashMap
	 *
	 * @param function
	 *            the function to store
	 * @return the function's name as a Character object
	 */
	public Character storeFunction(Function function) {
		if (functions.containsValue(function))
			return functions.inverse().get(function);
		final char name = getNextName();
		function.setName(name);
		functions.put(name, function);
		System.out.println(functions.get(names[functions.size() - 1]));
		return name;
	}
	
	/**
	 * Get the next function's name
	 *
	 * @return the new name
	 */
	public char getNextName() {
		return names[functions.size()];
	}
	
	/**
	 * Get the function for key name
	 *
	 * @param name
	 *            the key
	 * @return the function
	 */
	public Function getFunction(char name) {
		return functions.get(name);
	}
	
	/**
	 * Get the name of a function
	 *
	 * @param function
	 *            the function to find the name for
	 * @return the name as a Character
	 */
	public char getName(Function function) {
		return functions.inverse().get(function);
	}
	
	/**
	 * Get the latest function
	 *
	 * @return the requested function
	 */
	public Function getLatestFunction() {
		return functions.get(names[functions.size() - 1]);
	}

	/**
	 * Get all functions
	 *
	 * @return all functions as an array
	 */
	public Function[] getAllFunctions() {
		final ArrayList<Function> functionsArrayList = new ArrayList<>();
		for (final Entry<Character, Function> entry : functions.entrySet())
			functionsArrayList.add(entry.getValue());
		return functionsArrayList.toArray(new Function[functionsArrayList.size()]);
	}

	/**
	 * Debug method: displays the content of 'functions'
	 */
	public void displayFunctions() {
		System.out.println(functions);
	}
	
}
