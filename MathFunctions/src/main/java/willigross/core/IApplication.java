package willigross.core;

public interface IApplication {

	/**
	 * Guarantees that every implementation of the api can return the controller it uses
	 * 
	 * @return the controller of the api implementation
	 */
	public Controller getController();

}
