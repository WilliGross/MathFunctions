/*
 * Base code by DeBukkIt: https://github.com/DeBukkIt/SimpleFileStorage Modified by WilliGross for own needs
 */
package willigrossBubble.data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorage {

	private static final Logger		logger	= LoggerFactory.getLogger(FileStorage.class);
	
	private final File				storageFile;
	private HashMap<String, Object>	storageMap;

	/**
	 * Creates a FileStorage. It allows you to store<br>
	 * your serializable object in a file using a key<br>
	 * for identification and to read it somewhen later.
	 *
	 * @param file The file your data shall be stored in
	 * @throws IOException if your File cannot be created
	 * @throws IllegalArgumentException if your File is a directory
	 * @throws StreamCorruptedException or EOFException if file is corrupted
	 */
	public FileStorage(File file) throws IOException, IllegalArgumentException, StreamCorruptedException, EOFException,
			ClassNotFoundException {
		logger.info("FileStorage object created"); //$NON-NLS-1$
		storageFile = file;

		if (storageFile.isDirectory()) {
			final IllegalArgumentException e = new IllegalArgumentException(
					Strings.getStringAsHTML("FileStorage.exception_directory")); //$NON-NLS-1$
			logger.error("'File' argument is a directory: ", e); //$NON-NLS-1$
			throw e;
		}

		if (storageFile.createNewFile()) {
			logger.info("Storage file created"); //$NON-NLS-1$
			storageMap = new HashMap<>();
			save();
		} else
			load();
	}

	/**
	 * Saves the HashMap into the File
	 */
	private void save() {
		logger.info("Saving storage file"); //$NON-NLS-1$
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageFile))) {
			oos.writeObject(storageMap);
		} catch (final Exception e) {
			logger.error("Caught {} when saving storage file: ", e.getClass().getName(), e); //$NON-NLS-1$
		}
	}

	/**
	 * Loads the File into the HashMap
	 *
	 * @throws StreamCorruptedException or EOFException if file is corrupted
	 */
	@SuppressWarnings("unchecked")
	private void load() throws StreamCorruptedException, EOFException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storageFile))) {
			logger.info("Reading storage file"); //$NON-NLS-1$
			storageMap = (HashMap<String, Object>) ois.readObject();
		} catch (StreamCorruptedException | EOFException | ClassNotFoundException e) {
			logger.error("Caught {} when loading storage file: ", e.getClass().getName(), e); //$NON-NLS-1$
			throw e;
		} catch (final IOException e) {
			logger.error("Caught {} when loading storage file: ", e.getClass().getName(), e); //$NON-NLS-1$
		}
	}

	/**
	 * Stores an Object o using a String key for later identification
	 *
	 * @param key The key as String.
	 * @param o The Object.
	 */
	public void store(String key, Object o) {
		logger.info("Storing Object {} with key {}", o, key); //$NON-NLS-1$
		storageMap.put(key, o);
		save();
	}

	/**
	 * Reads your object from the storage
	 *
	 * @param key The key the object is available under
	 * @return your Object or null if nothing was found for <i>key</i>
	 */
	public Object get(String key) {
		logger.info("Retrieving object for key {} from storage map", key); //$NON-NLS-1$
		return storageMap.get(key);
	}

	/**
	 * Gives you the first key for a stored object
	 *
	 * @param obj The object you want to get the key for
	 * @return the first key for your object or null if nothing was found for <i>obj</i>
	 */
	public String getFirstKey(Object obj) {
		logger.info("Searching key for Object {}...", obj); //$NON-NLS-1$
		for (final Entry<String, Object> entry : storageMap.entrySet())
			if (obj.equals(entry.getValue())) {
				final String key = entry.getKey();
				logger.info("Object {} is stored under {}", obj, key); //$NON-NLS-1$
				return key;
			}
		logger.info("Object {} wasn't found", obj); //$NON-NLS-1$
		return null;
	}

	/**
	 * All stored objects in an ArrayList of Objects
	 *
	 * @return all stored objects in an ArrayList of Objects
	 */
	public ArrayList<Object> getAllAsArrayList() {
		final ArrayList<Object> result = new ArrayList<>();
		for (final Object c : storageMap.values())
			result.add(c);
		logger.info("Converting storage map into array list"); //$NON-NLS-1$
		return result;
	}

	/**
	 * All stored objects in a HashMap of Strings and Objects
	 *
	 * @return all stored objects in a HashMap of Strings and Objects
	 */
	public HashMap<String, Object> getAll() {
		return storageMap;
	}

	/**
	 * Prints all stored keys with corresponding objects
	 */
	public void printAll() {
		for (final String cKey : storageMap.keySet())
			System.out.println(cKey + " :: " + storageMap.get(cKey)); //$NON-NLS-1$
	}

	/**
	 * Removes an Key-Object pair from the storage
	 *
	 * @param key
	 */
	public void remove(String key) {
		storageMap.remove(key);
		logger.info("Removed object for key {}", key); //$NON-NLS-1$
		save();
	}

	/**
	 * Removes an Key-Object pair from the storage
	 *
	 * @param obj
	 */
	public void remove(Object obj) {
		final Iterator<Entry<String, Object>> iterator = storageMap.entrySet().iterator();
		while (iterator.hasNext()) {
			final Entry<String, Object> entry = iterator.next();
			if (obj.equals(entry.getValue()))
				iterator.remove();
		}
		logger.info("Removed object {}", obj); //$NON-NLS-1$
		save();
	}

	/**
	 * Checks whether a key is registerd
	 *
	 * @param key The Key.
	 * @return true if an object is available for that key
	 */
	public boolean hasKey(String key) {
		return storageMap.containsKey(key);
	}

	/**
	 * Checks whether an object is stored at all
	 *
	 * @param o The Object.
	 * @return true if the object is stored
	 */
	public boolean hasObject(Object o) {
		return storageMap.containsValue(o);
	}

	/**
	 * Return a String representation of the HashMap<br>
	 * containing all the key-object pairs.
	 */
	@Override
	public String toString() {
		String s = ""; //$NON-NLS-1$
		for (final String cKey : storageMap.keySet())
			s += cKey + " :: " + storageMap.get(cKey) + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		return s.trim();
	}

}
