package willigross.core.logic;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBiMap;

/**
 * This is the recommended implementation of ILogicController
 *
 * @see willigross.core.logic.ILogicController
 */
public class MainLogic implements ILogicController {

	private static final Logger						logger		= LoggerFactory.getLogger(MainLogic.class);
	
	/** The list where all functions are stored */
	private final HashBiMap<Character, Function>	functions	= HashBiMap.create(FunctionNames.getNames().length);
	
	@Override
	public Character storeFunction(Function function) {
		if (functions.containsValue(function))
			return functions.inverse().get(function);
		final char name = getNextName();
		function.setName(name);
		functions.put(name, function);
		logger.info("Stored {} in  HashBiMap", function); //$NON-NLS-1$
		return name;
	}
	
	@Override
	public void storeAllFunctions(@SuppressWarnings("hiding") Function[] functions) {
		logger.info("Storing all functions from array..."); //$NON-NLS-1$
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
		logger.debug("{}", functions); //$NON-NLS-1$
	}
	
}
