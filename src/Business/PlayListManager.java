package Business;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.jaudiotagger.audio.AudioFile;

import Business.Track;

public class PlayListManager {

	private HashMap<String, Playlist> playLists = new HashMap<String, Playlist>();

	public PlayListManager() {
		loadPlayList("");
	}

	public HashMap<String, Playlist> getPlayLists() {
		return playLists;
	}

	public Playlist getPlayList(String name) {
		return playLists.get(name);
	}

	public Track getTrack(Playlist pl, String title) {

		ArrayList<Track> tracks = pl.getList();

		for (Track t : tracks) {
			if (t.getTitle().equals(title)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Diese Methode laed die Playlisten, die als erweiterte m3us auf dem Speicher abgelegt sind
	 * 
	 * 
	 * @param s
	 */
	

	private void loadPlayList(String s) {
		ArrayList<File> m3uFiles = M3UManager.getM3Us(s);
		ArrayList<Track> tracks;
		Playlist pl;
		String[] title;
		for (File f : m3uFiles) {
			title = f.getName().split(Pattern.quote("."));
			pl = new Playlist(title[0], M3UManager.getTracksfromM3U(f));

			playLists.put(pl.getTitle(), pl);

		}

	}

	public void print() {
		ArrayList<Track> tracks;
		for (Playlist p : playLists.values()) {
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
}
