package Business;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Track {
	private long id;
	private String title;
	private int length;
	private String albumTitle;
	private String band;
	private String soundFile;

	public Track(long id, String title, int length, String albumTitle, String band, String soundFile) {
		this.id = id;
		this.title = title;
		this.length = length;
		this.albumTitle = albumTitle;
		this.band = band;
		this.soundFile = soundFile;
	}
	
	public Track(String title, int length, String interpret, String path){
		this(0, title, length, "null",interpret, path);
	}
	
	/*
	 * Kunstruktor fuer den Audiotager
	 * 
	 */
	
	public Track(File f){
		
			this(f.getName(), 0, "unbekannt", f.getAbsolutePath());
			
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSoundFile() {
		return soundFile;
	}

	public void setSoundFile(String soundFile) {
		this.soundFile = soundFile;
	}

}
