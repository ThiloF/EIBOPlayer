package business;

import java.io.File;

public class Track {
	private String title;
	private int length;
	private String albumTitle;
	private String interpret;
	private File soundFile;

	public Track(String title, int length, String interpret, File soundFile) {
		this.title = title;
		this.length = length;
		this.interpret = interpret;
		this.soundFile = soundFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getBand() {
		return interpret;
	}

	public void setBand(String band) {
		this.interpret = band;
	}

	public File getSoundFile() {
		return soundFile;
	}

}
