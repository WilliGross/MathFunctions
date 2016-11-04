package willigross.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import willigross.core.data.IDataController;
import willigross.core.gui.IGUIController;
import willigross.core.logic.ILogicController;

/**
 * This is the main API class. It organizes different parts of the program. The controller will hold references to the
 * logic controller, data controller and gui controller.
 *
 * @see willigross.core.logic.ILogicController
 * @see willigross.core.data.IDataController
 * @see willigross.core.gui.IGUIController
 */
public class Controller {
	
	private static final Logger		logger	= LoggerFactory.getLogger(Controller.class);
	
	/** Instance */
	private static Controller		instance;

	/** Caller */
	private static IApplication		caller;
	
	/* Controller objects */
	private final ILogicController	logicController;
	private final IDataController	dataController;
	private final IGUIController	guiController;

	public Controller(IApplication caller, ILogicController logicController, IDataController dataController,
			IGUIController guiController) { //this reference in constructors of sub-controllers is used for communication purposes, the main controller might have a log function implemented later -> java.util.logging.Logger
		logger.info("Creating controller..."); //$NON-NLS-1$
		Controller.caller = caller; //TODO Make less dirty
		this.logicController = logicController;
		this.dataController = dataController;
		this.guiController = guiController;
		logger.info("Subcontrollers initialized"); //$NON-NLS-1$

		logicController.storeAllFunctions(dataController.loadFunctions());
		logger.info("Functions loaded from data controller and stored in logic controller"); //$NON-NLS-1$

		guiController.start(this);

		logger.info("Controller created"); //$NON-NLS-1$
	}
	
	public Controller(IApplication caller, ILogicController logicController, IGUIController guiController) { //this reference in constructors of sub-controllers is used for communication purposes, the main controller might have a log function implemented later -> java.util.logging.Logger
		logger.info("Creating controller..."); //$NON-NLS-1$
		logger.info("Initiazlizing controller without DataController");
		Controller.caller = caller; //TODO Make less dirty
		this.logicController = logicController;
		dataController = null;
		this.guiController = guiController;
		logger.info("Subcontrollers initialized"); //$NON-NLS-1$

		guiController.start(this);

		logger.info("Controller created"); //$NON-NLS-1$
	}

	/**
	 * @return the instance
	 */
	public static Controller getInstance() {
		if (instance == null)
			instance = caller.getController();
		return instance;
	}

	/**
	 * @param controller the controller to set as the instance
	 */
	public static void setInstance(Controller controller) {
		Controller.instance = controller;
		logger.info("New instance set"); //$NON-NLS-1$
	}

	/**
	 * @return the logicController
	 */
	public ILogicController getLogicController() {
		return logicController;
	}
	
	/**
	 * @return the dataController
	 */
	public IDataController getDataController() {
		return dataController;
	}
	
	/**
	 * @return the guiController
	 */
	public IGUIController getGUIController() {
		return guiController;
	}
	
	/**
	 * Is a data controller initialized?
	 *
	 * @return true if a data controller is set, otherwise false
	 */
	public boolean isDataControllerInitialized() {
		if (dataController == null)
			return false;
		return true;
	}

}
