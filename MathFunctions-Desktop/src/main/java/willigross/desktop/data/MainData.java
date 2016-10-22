package willigross.desktop.data;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.data.IDataController;
import willigross.core.logic.Function;
import willigross.core.logic.FunctionNames;
import willigross.desktop.gui.FrameMain;

public class MainData implements IDataController {

	private static final Logger	logger		= LoggerFactory.getLogger(MainData.class);

	/** The file where functions are saved to make them survive a program restart */
	private final File			storageFile	= new File(
			MainData.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(0,
					MainData.class.getProtectionDomain().getCodeSource().getLocation().getFile().lastIndexOf('/') + 1)
					+ Strings.getString("MainData.functionStorageFileName"));											//$NON-NLS-1$
	
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
			logger.error("Caught {} when trying to save a function: ", e.getClass().getName(), e); //$NON-NLS-1$
		}
	}
	
	@Override
	public void removeFunctionFromFile(Function function) {
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			fileStorage.remove(function);
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			logger.error("Caught {} when trying to remove a function: ", e.getClass().getName(), e); //$NON-NLS-1$
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
			logger.error("Caught {} when trying to check if a function is saaved: ", e.getClass().getName(), e); //$NON-NLS-1$
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
			
		} catch (StreamCorruptedException | EOFException | ClassNotFoundException e) {
			logger.error("Caught {} when trying to load functions.: ", e.getClass().getName(), e); //$NON-NLS-1$
			deleteStorageFile();
		} catch (IllegalArgumentException | IOException e) {
			logger.error("Caught {} when trying to load functions: ", e.getClass().getName(), e); //$NON-NLS-1$
		}
		return functions.toArray(new Function[functions.size()]);
	}
	
	/**
	 * Delete the FunctionsDat file
	 */
	public void deleteStorageFile() {
		logger.info("Storage file corrupted - deleting it!"); //$NON-NLS-1$
		FrameMain.getInstance().displayCorruptedFileWarning();
		storageFile.delete();
	}
	
	/**
	 * Debug method: displays the content of 'Functions.dat'
	 */
	public String displayStorageFile() {
		try {
			final FileStorage fileStorage = new FileStorage(storageFile);
			
			return fileStorage.getAll().toString();
		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			logger.error("Caught {}: ", e.getClass().getName(), e); //$NON-NLS-1$
			return null;
		}

	}
	
}
