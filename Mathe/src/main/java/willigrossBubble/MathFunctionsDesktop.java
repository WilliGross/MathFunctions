package willigrossBubble;

import willigrossBubble.core.Controller;
import willigrossBubble.core.IApplication;
import willigrossBubble.core.logic.MainLogic;
import willigrossBubble.data.MainData;
import willigrossBubble.gui.FrameMain;

/**
 * This is the main class for the MathFunctions desktop application. The main action it performs is creating a
 * controller and saving a reference to it. This is separated to keep the API clean and allow it to be used on mobile
 * operating systems like android in the future.
 */
public class MathFunctionsDesktop implements IApplication {
	
	private static MathFunctionsDesktop	instance;
	private final Controller			controller;
	
	/**
	 * Application entry point
	 *
	 * @param args parameters when starting the application, for example to force a specific language
	 */
	public static void main(String[] args) {
		instance = new MathFunctionsDesktop();
	}

	private MathFunctionsDesktop() {
		controller = new Controller(this, new MainLogic(), new MainData(), new FrameMain());
	}
	
	/**
	 * @return the controller
	 */
	@Override
	public Controller getController() {
		return controller;
	}
	
	/**
	 * @return the instance
	 */
	public static MathFunctionsDesktop getInstance() {
		return instance;
	}
	
}
