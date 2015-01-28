package business;

import java.io.File;

/**
 * Track-Klasse. Beinhaltet abstrakt einen Track
 * 
 * @author fkoen001
 *
 */
public class Track {
	private String title;
	private int length;
	private String interpret;
	private File soundFile;

	/**
	 * Erstellt einen Track.
	 * 
	 * @param title
	 *            Titel des Tracks
	 * @param length
	 *            Länge des Tracks in Sekunden
	 * @param interpret
	 *            Interpret des Tracks
	 * @param soundFile
	 *            Soundfile des Tracks
	 */
	public Track(String title, int length, String interpret, File soundFile) {
		this.title = title;
		this.length = length;
		this.interpret = interpret;
		this.soundFile = soundFile;
	}

	/**
	 * Gibt den Titel des Tracks zurück.
	 * 
	 * @return Titel des Tracks
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setzt den Titel des Tracks
	 * @param title neuer Titel
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gibt die Länge des Tracks zurück
	 * @return Länge des Tracks in Sekunden
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Setzt die Länge des Tracks
	 * @param length Tracklänge in Sekunden
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gibt den Interpreten des Tracks zurück
	 * @return Track-Interpret
	 */
	public String getInterpret() {
		return interpret;
	}

	/**
	 * Setzt den Interpreten des Tracks
	 * @param interpret Track-Interpret
	 */
	public void setBand(String interpret) {
		this.interpret = interpret;
	}

	/**
	 * Gibt die Sounddatei des Tracks zurück
	 * @return Sounddatei des Tracks als File-Objekt
	 */
	public File getSoundFile() {
		return soundFile;
	}

}
