package business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse stellt eine abstrakte Sammlung von Playlist-Objekten dar und bietet entsprechende Funktionalitäten zum Verwalten an.
 * 
 * @author fkoen001
 *
 */
public class Library {

	private List<Playlist> playlists = new ArrayList<Playlist>();

	public Library() {
		load();
	}

	/**
	 * Entfernt eine Playlist, auch vom Dateisystem
	 * 
	 * @param playlist
	 *            zu löschende Playlist
	 * @return true, wenn eine Playlist entfernt wurde
	 */
	public boolean remove(Playlist playlist) {
		FileManager.deletePlaylist(playlist);
		return playlists.remove(playlist);
	}

	/**
	 * Fügt eine Playlist der Liste hinzu. Speichert sie auch im Dateisystem
	 * 
	 * @param playlist
	 *            Hinzuzufügende Playlist
	 * @return true, wenn die Playlist hinzugefügt wurde
	 */
	public boolean add(Playlist playlist) {
		save(playlist);
		return playlists.add(playlist);
	}

	/**
	 * Gibt die Liste der gespeicherten Playlisten zurück
	 * 
	 * @return Playlist-Liste
	 */
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	/**
	 * Diese Methode laed die Playlisten, die als erweiterte m3us auf dem Speicher abgelegt sind
	 * 
	 * 
	 * @param root
	 */
	private void load() {
		ArrayList<File> m3uFiles = FileManager.getM3Us();
		Playlist pl;
		for (File f : m3uFiles) {
			pl = FileManager.getPlaylistFromM3U(f);
			if (pl != null) {
				playlists.add(pl);
			}
		}
	}

	/**
	 * Speichert alle Playlisten (auf die Festplatte)
	 */
	public void saveAll() {
		for (Playlist playlist : playlists) {
			save(playlist);
		}
	}

	/**
	 * Speichert eine Playlist (auf die Festplatte)
	 * @param playlist
	 */
	public void save(Playlist playlist) {
		FileManager.writePlaylistToM3U(playlist);
	}

	/**
	 * Gibt die gesamte Liste an Playlisten zu Debuggingzwecken auf der Konsole aus
	 */
	public void print() {
		ArrayList<Track> tracks;
		for (Playlist p : playlists) {
			System.out.println(p.getTitle());
			tracks = p.getTracks();
			for (Track t : tracks) {
				System.out.println(t.getSoundFile());
			}
		}
	}

	/**
	 * Entfernt einen Track aus einer Playlist (und speichert das auch)
	 * @param playlist Betroffene Playlist
	 * @param index Index der zu entfernenden Lieds
	 */
	public void removeTrackFromPlaylist(Playlist playlist, int index) {
		playlist.tracks.remove(index);
		save(playlist);
	}
}
