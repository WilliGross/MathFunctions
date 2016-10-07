package willigrossBubble.data;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import willigrossBubble.core.Controller;
import willigrossBubble.core.data.IDataController;
import willigrossBubble.core.logic.Function;
import willigrossBubble.core.logic.FunctionNames;
import willigrossBubble.gui.FrameMain;

public class MainData implements IDataController {

	/** Reference to the main controller */
	private Controller controller; //Will be used for Logger
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	/** The file where functions are saved to make them survive a program restart */
	private final File storageFile = new File(
			MainData.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(0,
					MainData.class.getProtectionDomain().getCodeSource().getLocation().getFile().lastIndexOf('/') + 1)
					+ Strings.getString("MainLogic.functionStorageFileName")); //$NON-NLS-1$
	
	@Override
	public void saveFunctionInFile(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			for (int i = 0; i < FunctionNames.getNames().length; i++)
				if (!fileStorage.hasKey("" + FunctionNames.getNames()[i])) { //$NON-NLS-1$
					fileStorage.store("" + FunctionNames.getNames()[i], function); //$NON-NLS-1$
					break;
				}
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void removeFunctionFromFile(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			fileStorage.remove(function);
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	@Override
	public boolean isFunctionSaved(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			
			for (final Object f : fileStorage.getAllAsArrayList().toArray())
				if (((Function) f).equals(function))
					return true;

		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public Function[] loadFunctions() {
		final ArrayList<Function> functions = new ArrayList<>();
		Function function = null;
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			for (final char name : FunctionNames.getNames()) {
				function = (Function) fileStorage.get("" + name); //$NON-NLS-1$
				if (function != null)
					functions.add(function);
			}
			
		} catch (@SuppressWarnings("unused") StreamCorruptedException | EOFException | ClassNotFoundException e) {
			deleteStorageFile();
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return functions.toArray(new Function[functions.size()]);
	}
	
	/**
	 * Delete the FunctionsDat file
	 */
	public void deleteStorageFile() {
		JOptionPane.showMessageDialog(((FrameMain) Controller.getInstance().getGUIController()),
				Strings.getStringAsHTML("MainLogic.functionStorageFileCorrupted_message"), //$NON-NLS-1$
				Strings.getString("MainLogic.functionStorageFileCorrupted_title"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		storageFile.delete();
	}
	
	/**
	 * Debug method: displays the content of 'Functions.dat'
	 */
	public void displayStorageFile() {
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			
			System.out.println(fileStorage.getAll());
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
}
