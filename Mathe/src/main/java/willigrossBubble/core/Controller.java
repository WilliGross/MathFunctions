package willigrossBubble.core;

import willigrossBubble.core.data.IDataController;
import willigrossBubble.core.gui.IGUIController;
import willigrossBubble.core.logic.ILogicController;

/**
 * This is the main API class. It organizes different parts of the program. The controller will hold references to the
 * logic controller, data controller and gui controller.
 *
 * @see willigrossBubble.core.logic.ILogicController
 * @see willigrossBubble.core.data.IDataController
 * @see willigrossBubble.core.gui.IGUIController
 */
public class Controller {
	
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
		Controller.caller = caller; //TODO Make less dirty
		this.logicController = logicController;
		this.dataController = dataController;
		this.guiController = guiController;

		logicController.setController(this);
		dataController.setController(this);
		guiController.setController(this);

		logicController.storeAllFunctions(dataController.loadFunctions());
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

}
