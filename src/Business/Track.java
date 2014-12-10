package Business;

import java.io.File;

public class Track {
	private String title;
	private int length;
	private String albumTitle;
	private String band;
	private File soundFile;

	public Track(String title, int length, String albumTitle, String band, File soundFile) {
		this.title = title;
		this.length = length;
		this.albumTitle = albumTitle;
		this.band = band;
		this.soundFile = soundFile;
	}
	
	public Track(String title, int length, String interpret, File path){
		this(title, length, "null",interpret, path);
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
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public File getSoundFile() {
		return soundFile;
	}

}
