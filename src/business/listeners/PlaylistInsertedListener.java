package business.listeners;

import business.Playlist;

/**
 * Listener-Interface für PlaylistInserted Events
 * 
 * @author fkoen001
 *
 */
public interface PlaylistInsertedListener {

	/**
	 * Wird aufgerufen, wenn eine Playlist in den Player eingelegt wurde
	 * 
	 * @param playlist
	 *            nun eingelegte Playlist
	 */
	public void playlistInserted(Playlist playlist);

}
