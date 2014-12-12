package Business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Library {

	private List<Playlist> playlists = new ArrayList<Playlist>();

	public Library() {
		load();
	}

	public boolean remove(Playlist playlist) {
		return playlists.remove(playlist);
	}

	public boolean add(Playlist playlist) {
		return playlists.add(playlist);
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	/**
	 * Diese Methode laed die Playlisten, die als erweiterte m3us auf dem
	 * Speicher abgelegt sind
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

	public void saveAll() {
		for (Playlist playlist : playlists) {
			save(playlist);
		}
	}

	public void save(Playlist playlist) {
		FileManager.writePlaylistToM3U(playlist);
	}

	/*
	 * Dient nur zum debuggen SPÄTER RAUSSCHMEISSEN !!!
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

	public void removeTrackFromPlaylist(Playlist playlist, int index) {
		playlist.list.remove(index);
		save(playlist);
	}
}
