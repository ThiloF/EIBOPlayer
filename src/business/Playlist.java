package business;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Playlist-Klasse. Stellt eine Playlist mit mehreren Liedern dar.
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class Playlist {
	private String title;
	private BufferedImage coverImage;
	ArrayList<Track> tracks = new ArrayList<Track>();

	public static BufferedImage DEFAULT_COVERIMAGE;
	static {
		try {
			DEFAULT_COVERIMAGE = ImageIO.read(new File("defaultcover.png"));
		} catch (IOException e) {
			System.err.println("The default coverimage is missing! Oh no!");
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt eine neue Playlist
	 * 
	 * @param title
	 *            Titel der Playlist
	 * @param coverImage
	 *            Coverbild der Playlist
	 * @param tracks
	 *            Liste der Tracks, die die Playlist beinhalten soll
	 */
	public Playlist(String title, BufferedImage coverImage, ArrayList<Track> tracks) {
		this.title = title;
		if (coverImage == null) {
			this.coverImage = DEFAULT_COVERIMAGE;
		} else {
			this.coverImage = coverImage;
		}
		this.tracks = tracks;
	}

	/**
	 * Erstellt eine neue Playlist
	 * 
	 * @param title
	 *            Titel der Playlist
	 * @param tracks
	 *            Liste der Tracks, die die Playlist beinhalten soll
	 */
	public Playlist(String title, ArrayList<Track> tracks) {
		this(title, null, tracks);
	}

	/**
	 * Gibt den Track zurück, der dem Titel entspricht
	 * 
	 * @param title
	 *            Titel des zu findenden Tracks
	 * @return Track, der dem Titel entspricht, oder null
	 */
	public Track getTrackByTitle(String title) {
		for (Track t : tracks) {
			if (t.getTitle().equals(title)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Gibt die Tracks als Liste zurück
	 * 
	 * @return Tracks als ArrayList
	 */
	public ArrayList<Track> getTracks() {
		return tracks;
	}

	/**
	 * Gibt den Playlist-Titel zurück
	 * 
	 * @return Playlist-Titel
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setzt den Titel der Playlist
	 * 
	 * @param title
	 *            neuer Titel
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Fügt der Playlist einen Track hinzu
	 * 
	 * @param track
	 *            neuer Track
	 */
	public void addTrack(Track track) {
		tracks.add(track);
	}

	/**
	 * Gibt zurück, wie viele Tracks die Playlist beinhaltet
	 * 
	 * @return Anzahl an Tracks
	 */
	public int numberOfTracks() {
		return tracks.size();
	}

	/**
	 * Gibt einen Track anhand der Tracknummer zurück
	 * 
	 * @param trackNo
	 * @return Der Track-Nr entsprechender Track, oder null
	 */
	public Track getTrack(int trackNo) {
		if (trackNo < 0 || trackNo >= tracks.size()) {
			return null;
		}
		return tracks.get(trackNo);
	}

	/**
	 * Gibt das Coverbild zurück
	 * 
	 * @return Coverbild als BufferedImage
	 */
	public BufferedImage getCoverImage() {
		return coverImage;
	}

	/**
	 * Gibt die Playlist zu Debuggingzwecken auf der Konsole aus
	 */
	public void printList() {
		for (Track t : tracks) {
			System.out.println("Name: " + t.getTitle());
		}
	}

}
