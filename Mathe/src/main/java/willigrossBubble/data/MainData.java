package willigrossBubble.data;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import willigrossBubble.gui.FrameMain;
import willigrossBubble.logic.Function;
import willigrossBubble.logic.MainLogic;

public class MainData {
	
	/** The file where functions are saved to make them survive a program restart */
	private final File functionsDat = new File(
			MainData.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(0,
					MainData.class.getProtectionDomain().getCodeSource().getLocation().getFile().lastIndexOf('/') + 1)
					+ Strings.getString("MainLogic.functionStorageFileName")); //$NON-NLS-1$
	
	/**
	 * Save a function in 'Functions.dat'
	 *
	 * @param function
	 *            the function to be saved
	 */
	public void saveFunctionInFile(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(functionsDat);
			for (int i = 0; i < MainLogic.names.length; i++)
				if (!fileStorage.hasKey("" + MainLogic.names[i])) { //$NON-NLS-1$
					fileStorage.store("" + MainLogic.names[i], function); //$NON-NLS-1$
					break;
				}
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Removes a function from the save file but not from the functions list (-> effective after restart)
	 *
	 * @param function
	 *            - the function to remove
	 */
	public void removeFunctionFromFile(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(functionsDat);
			fileStorage.remove(function);
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Loads all the functions from the save file and adds them to the functions map
	 */
	public HashMap<Character, Function> loadFunctions() {
		final HashMap<Character, Function> functions = new HashMap<>();
		Function function = null;
		try {
			final FileStorage fileStorage = new FileStorage(functionsDat);
			for (final char name : MainLogic.names) {
				function = (Function) fileStorage.get("" + name); //$NON-NLS-1$
				if (function != null)
					functions.put(MainLogic.names[functions.size()], function);
			}
			
		} catch (@SuppressWarnings("unused") StreamCorruptedException | EOFException | ClassNotFoundException e) {
			deleteFunctionsDat();
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return functions;
	}
	
	/**
	 * Delete the FunctionsDat file
	 */
	public void deleteFunctionsDat() {
		JOptionPane.showMessageDialog(FrameMain.getInstance(),
				Strings.getStringAsHTML("MainLogic.functionStorageFileCorrupted_message"), //$NON-NLS-1$
				Strings.getString("MainLogic.functionStorageFileCorrupted_title"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		functionsDat.delete();
	}
	
	/**
	 * Checks if a function is saved in Functions.dat
	 *
	 * @param function
	 *            the function to check
	 * @return true if the function is stored in file, otherwise false
	 */
	public boolean isFunctionSaved(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(functionsDat);
			
			for (final Object f : fileStorage.getAllAsArrayList().toArray())
				if (((Function) f).equals(function))
					return true;

		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Debug method: displays the content of 'Functions.dat'
	 */
	public void displayFunctionsDat() {
		try {
			final FileStorage fileStorage = new FileStorage(functionsDat);
			
			System.out.println(fileStorage.getAll());
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
}
