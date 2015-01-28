package business.listeners;

/**
 * Listener-Interface für TrackStopped Events
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public interface TrackStoppedListener {

	/**
	 * Wird aufgerufen, wenn ein Track stoppt
	 * 
	 * @param cancelled
	 *            true, wenn der Track abgebrochen wurde. False, falls er natürlich endete
	 */
	public void trackStopped(boolean cancelled);

}
