package Business;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MusicPlayer implements IPlayer {

	private Library library;

	private Playlist currentPlaylist;
	private Track currentTrack;

	private Minim minim;
	private AudioPlayer currentPlayer = null;
	
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public MusicPlayer(Library lib) {
		library = lib;
		minim = new Minim(new MinimHelper());
	}

	public void addPropertyChangeListener(PropertyChangeListener l){
		changes.addPropertyChangeListener(l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l){
		changes.removePropertyChangeListener(l);
	}
	
	public void close() {
		stop();
		minim.dispose();
	}

	@Override
	public void play() {
		stop();
		currentPlayer = minim.loadFile(currentTrack.getSoundFile().getPath());
		currentPlayer.play();
		changes.firePropertyChange("status", new String("play"), "stop");
	}

	@Override
	public void stop() {
		if (currentPlayer != null) {
			currentPlayer.close();
		}
	}

	@Override
	public void skip() {
		int index = currentPlaylist.getTracks().indexOf(currentTrack);
		selectTrackNumber(index + 1);
	}

	@Override
	public void skipBack() {
		int index = currentPlaylist.getTracks().indexOf(currentTrack);
		selectTrackNumber(index - 1);
	}

	public void selectTrackNumber(int num) {
		int length = currentPlaylist.getTracks().size();
		num = (num + length) % length;
	}

	@Override
	public Playlist getPlaylist() {
		return currentPlaylist;
	}

	public Library getLibrary() {
		return library;
	}

	@Override
	public void pause() {
		currentPlayer.pause();
	}

	@Override
	public void unpause() {
		currentPlayer.play();
	}

	@Override
	public void selectTrack(Track track) {
		currentTrack = track;
	}

	@Override
	public void selectPlaylist(Playlist playlist) {
		currentPlaylist = playlist;
	}

	@Override
	public Track getTrack() {
		return currentTrack;
	}

	@Override
	public void skipTo(int offset) {
		currentPlayer.skip(offset - currentPlayer.position());
	}
	
	public void save() {
		library.save(currentPlaylist);
	}
	
	public void addTrackToPlaylist(Track track) {
		currentPlaylist.addTrack(track);
		library.save(currentPlaylist);
	}
	
	public Track getTrackFromFile(File file) {
		return FileManager.getTrackFromFile(file, minim);
	}

}
