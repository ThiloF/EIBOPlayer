package Business;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.imageio.stream.FileImageInputStream;

import com.sun.media.jfxmedia.track.AudioTrack;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class MP3Player {

	private Player pl;
	private MediaPlayer mp;
	private boolean playing = false;
	private Track actTrack = null;

	public MP3Player() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Track getActTrack() {
		return actTrack;
	}



	public void setActTrack(Track actTrack) {
		this.actTrack = actTrack;
	}



	public boolean isPlaying(){
		return playing;
	}
	
	public void playerStatus(boolean is){
		playing = is;
	}
	
	
	public void fxplay(){
		
		
		mp = new MediaPlayer(new Media(actTrack.getSoundFile()));
		mp.play();
		
	}
	
	
	public void play() {
			
			playing = true;
		try {
			pl = new Player(new FileInputStream(actTrack.getSoundFile()));
	        pl = new Player(new FileInputStream(actTrack.getSoundFile()));
			pl.play();
			
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void stop(){
		playing = false;
		pl.close();
	}

}
