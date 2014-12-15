package Business;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Playlist {
	private String title;
	private Image coverImage;
	ArrayList<Track> tracks = new ArrayList<Track>();

	public static Image DEFAULT_COVERIMAGE;
	static {
		try {
			DEFAULT_COVERIMAGE = ImageIO.read(new File("defaultcover.png"));
		} catch (IOException e) {
			System.err.println("The default coverimage is missing! Oh no!");
			e.printStackTrace();
		}
	}

	public Playlist(String title, Image coverImage, ArrayList<Track> tracks) {
		this.title = title;
		if (coverImage == null) {
			this.coverImage = DEFAULT_COVERIMAGE;
		} else {
			this.coverImage = coverImage;
		}
		this.tracks = tracks;
	}

	public Playlist(String title, ArrayList<Track> tracks) {
		this(title, null, tracks);
	}

	public Track getTitle(String title) {
		for (Track t : tracks) {
			if (t.getTitle().equals(title)) {
				return t;
			}
		}
		return null;
	}

	public ArrayList<Track> getTracks() {
		return tracks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addTrack(Track tr) {
		tracks.add(tr);
	}

	public int numberOfTracks() {
		return tracks.size();
	}

	public Track getTrack(int no) {
		if (no < 0 || no >= tracks.size())
			return null;
		return tracks.get(no);
	}
	
	public Image getCoverImage() {
		return coverImage;
	}

	public void printList() {
		for (Track t : tracks) {
			System.out.println("Name: " + t.getTitle());
		}
	}

}
