package Business;

import java.io.File;
import java.util.ArrayList;

public class Playlist {
	private String title;
	private File coverFile;
	ArrayList<Track> list = new ArrayList<Track>();

	public Playlist(String title, File coverFile, ArrayList<Track> tracks) {
		this.title = title;
		this.coverFile = coverFile;
		this.list = tracks;
	}

	public Playlist(String title, ArrayList<Track> tracks) {
		this(title, null, tracks);
	}

	public Track getTitle(String title) {
		for (Track t : list) {
			if (t.getTitle().equals(title)) {
				return t;
			}
		}
		return null;
	}

	public ArrayList<Track> getTracks() {
		return list;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addTrack(Track tr) {
		list.add(tr);
	}

	public int numberOfTracks() {
		return list.size();
	}

	public Track getTrack(int no) {
		if (no < 0 || no >= list.size()) return null;
		return list.get(no);
	}

	public void printList() {
		for (Track t : list) {
			System.out.println("Name: " + t.getTitle());
		}
	}

}
