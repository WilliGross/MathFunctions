package willigrossBubble;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;

import javax.swing.JOptionPane;

import com.google.common.collect.HashBiMap;

import willigrossBubble.gui.FrameMain;

public class MainLogic {
	
	/**A utility array with the names of functions, f to z then F to Z */
	private static char[] names = {'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	/**A list where all functions are stored */
	private HashBiMap<Character, Function> functions = HashBiMap.create(names.length);
	
	/** The file where functions are saved to make them survive a program restart */
	private File functionsDat = new File(MainLogic.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(0, MainLogic.class.getProtectionDomain().getCodeSource().getLocation().getFile().lastIndexOf('/') + 1) + Strings.getString("MainLogic.functionStorageFileName")); //$NON-NLS-1$
	
	/**
	 * Constructor of MainLogic that loads functions from file on call
	 */
	public MainLogic() {
		loadFunctions();
		displayFunctions();
	}
	
	/**
	 * @return the names
	 */
	public static char[] getNames() {
		return names;
	}

	/**
	 * Stores a function in the HasMap
	 * @param function the function to store
	 * @return the function's name as a Character object
	 */
	public Character storeFunction(Function function) {
		if (functions.containsValue(function))
			return functions.inverse().get(function);
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
		return functions.get(names[functions.size() - 1]);
	}
	
	/**
	 * Save a function in 'Functions.dat'
	 * @param function the function to be saved
	 */
	public void saveFunctionInFile(Function function) {
		try {
			FileStorage fileStorage = new FileStorage(functionsDat);
			for (int i = 0; i < names.length; i++) {
				if (! fileStorage.hasKey("" + names[i])) { //$NON-NLS-1$
					fileStorage.store("" + names[i], function); //$NON-NLS-1$
					break;
				}
			}
		} catch (IllegalArgumentException | IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Removes a function from the save file but not from the functions list (-> effective after restart)
	 * @param function - the function to remove
	 */
	public void removeFunctionFromFile(Function function) {
		try {
			FileStorage fileStorage = new FileStorage(functionsDat);
			fileStorage.remove(function);
		} catch (IllegalArgumentException | IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Loads all the functions from the save file and adds them to the functions map
	 */
	private void loadFunctions() {
		Function function = null;
		try {
			FileStorage fileStorage = new FileStorage(functionsDat);
			for (int i = 0; i < names.length; i++) {
				function = (Function) fileStorage.get("" + names[i]); //$NON-NLS-1$
				if (function != null)
					functions.put(getNextName(), function);
			}
			
		} catch (@SuppressWarnings("unused") StreamCorruptedException | EOFException e) {
			JOptionPane.showMessageDialog(FrameMain.getInstance(), Strings.getString("MainLogic.functionStorageFileCorrupted_message"), Strings.getString("MainLogic.functionStorageFileCorrupted_title"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			functionsDat.delete();
		} catch (IllegalArgumentException | IOException e) {
			System.err.println(e);
		}
	
	}

	/**
	 * Debug method: displays the content of 'Functions.dat'
	 */
	public void displayFunctionsDat() {
		try {
			FileStorage fileStorage = new FileStorage(functionsDat);
			
			System.out.println(fileStorage.getAll());
		} catch (IllegalArgumentException | IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Debug method: displays the content of 'functions'
	 */
	public void displayFunctions() {
		System.out.println(functions);
	}
	
}
