package willigrossBubble.core.logic;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.google.common.collect.HashBiMap;

import willigrossBubble.core.Controller;

/**
 * This is the recommended implementation of ILogicController
 * 
 * @see willigrossBubble.core.logic.ILogicController
 */
public class MainLogic implements ILogicController {

	/** Reference to the main controller */
	private Controller								controller;														//Will be used for Logger

	/** The list where all functions are stored */
	private final HashBiMap<Character, Function>	functions	= HashBiMap.create(FunctionNames.getNames().length);

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public Character storeFunction(Function function) {
		if (functions.containsValue(function))
			return functions.inverse().get(function);
		final char name = getNextName();
		function.setName(name);
		functions.put(name, function);
		System.out.println(getLatestFunction());
		return name;
	}

	@Override
	public void storeAllFunctions(@SuppressWarnings("hiding") Function[] functions) {
		for (final Function f : functions)
			storeFunction(f);
	}
	
	@Override
	public Function getFunction(char name) {
		return functions.get(name);
	}

	@Override
	public Function[] getAllFunctions() {
		final ArrayList<Function> functionsArrayList = new ArrayList<>();
		for (final Entry<Character, Function> entry : functions.entrySet())
			functionsArrayList.add(entry.getValue());
		return functionsArrayList.toArray(new Function[functionsArrayList.size()]);
	}

	@Override
	public Function getLatestFunction() {
		return functions.get(FunctionNames.getNames()[functions.size() - 1]);
	}

	@Override
	public char getName(Function function) {
		return functions.inverse().get(function);
	}

	@Override
	public char getNextName() {
		return FunctionNames.getNames()[functions.size()];
	}

	/**
	 * Debug method: displays the content of 'functions'
	 */
	public void displayFunctions() {
		System.out.println(functions);
	}

}
