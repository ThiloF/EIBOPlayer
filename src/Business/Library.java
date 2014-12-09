package Business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Library {

	private List<Playlist> playlists = new ArrayList<Playlist>();
	
	public Library() {
		loadPlayList("");
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
	 * Diese Methode laed die Playlisten, die als erweiterte m3us auf dem Speicher abgelegt sind
	 * 
	 * 
	 * @param s
	 */
	

	private void loadPlayList(String s) {
		ArrayList<File> m3uFiles = FileManager.getM3Us(s);
		ArrayList<Track> tracks;
		Playlist pl;
		String[] title;
		for (File f : m3uFiles) {
			title = f.getName().split(Pattern.quote("."));
			pl = new Playlist(title[0], FileManager.getTracksfromM3U(f));

			playlists.add(pl);

		}

	}
	/*
	 * Dient nur zum debuggen SPÄTER RAUSSCHMEISSEN !!!
	 * 
	 */

	public void print() {
		ArrayList<Track> tracks;
		for (Playlist p : playlists) {
			System.out.println(p.getTitle());
			tracks = p.getList();
			for (Track t : tracks) {
				System.out.println(t.getSoundFile());
			}
		}
	}

	/**
	 * Die Methode searchForMP3 durchläuft das Dateisystem ab dem im paramter
	 * angebenen Parameter f
	 * 
	 * @param f
	 * @param mp3list
	 * @return
	 */

	public ArrayList<File> searchForMP3(File f, ArrayList<File> mp3list) {
		if (f == null || !f.isDirectory()) {
			return null;
		}

		File[] fileArray = f.listFiles();

		for (File files : fileArray) {
			if (files.isDirectory()) {
				searchForMP3(files, mp3list);
			} else if (files.getPath().toString().toLowerCase().endsWith(".mp3")) {
				mp3list.add(files);
			}
		}
		return mp3list;
	}
	
	public void removeTrackFromPlaylist(Playlist pl, Track track){
		for(Track t: pl.list){
			if(t.equals(track)){
				pl.list.remove(t);
				return;
			}
		}
	}
}
