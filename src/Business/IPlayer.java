package Business;

public interface IPlayer {

	public void play();
	public void stop();
	public void skip();
	public void skipBack();
	public void cue(int offset);
	
	public Playlist getPlaylist();
	public Track getTrack();
	
	public void pause();
	
	public void close();
	
}
