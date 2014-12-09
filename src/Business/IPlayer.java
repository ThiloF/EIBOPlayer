package Business;

public interface IPlayer {

	public void play();
	public void stop();
	public void skip();
	public void skipBack();
	public void selectTrack(Track track);
	public void selectPlaylist(Playlist playlist);
	public void skipTo(int offset);
	
	public Playlist getPlaylist();
	public Track getTrack();
	
	public void pause();
	public void unpause();
	
	public void close();
	
}
