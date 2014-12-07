package Business;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;



import javax.imageio.stream.FileImageInputStream;



import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class MP3Player {

	private Player pl;
	
	private boolean playing = false;
	private HashMap<String, Playlist> playLists;
	private Track actTitle;
	private Playlist actPlaylist;
	private PlayListManager plm;
	
	public MP3Player() {
		// TODO Auto-generated constructor stub
		plm = new PlayListManager();
		playLists = plm.getPlayLists();
	}
	
	public PlayListManager getPlayListManager(){
		return plm;
	}
	
	public Track getActTrack() {
		return actTitle;
	}
	
	public Playlist getActPlaylist(){
		return actPlaylist;
	}
	
	public void setActPlaylist(Playlist pl){
		actPlaylist = pl;
	}
	
	public HashMap<String, Playlist> getPlayLists(){
		return playLists;
	}



	public void setActTrack(Track actTrack) {
		this.actTitle = actTrack;
	}



	public boolean isPlaying(){
		return playing;
	}
	
	public void playerStatus(boolean is){
		playing = is;
	}
	
	
	
	
	
	public void play() {
			
			playing = true;
		try {
			pl = new Player(new FileInputStream(actTitle.getSoundFile()));
	        pl = new Player(new FileInputStream(actTitle.getSoundFile()));
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
