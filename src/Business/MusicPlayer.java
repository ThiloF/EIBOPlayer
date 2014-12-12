package Business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Business.Listeners.PlaylistInsertedListener;
import Business.Listeners.TrackStartedListener;
import Business.Listeners.TrackStoppedListener;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MusicPlayer implements IPlayer {

	/*
	 * LISTENERS START
	 */

	private List<TrackStartedListener> trackStartedListeners = new ArrayList<>();
	private List<TrackStoppedListener> trackStoppedListeners = new ArrayList<>();
	private List<PlaylistInsertedListener> playlistInsertedListeners = new ArrayList<>();

	public void addTrackStartedListener(TrackStartedListener listener) {
		trackStartedListeners.add(listener);
	}

	public void addTrackStoppedListener(TrackStoppedListener listener) {
		trackStoppedListeners.add(listener);
	}

	public void addPlaylistInsertedListener(PlaylistInsertedListener listener) {
		playlistInsertedListeners.add(listener);
	}

	public void notifyTrackStartedListener() {
		trackStartedListeners.forEach(l -> l.trackStarted());
	}

	public void notifyTrackStoppedListener(boolean cancelled) {
		trackStoppedListeners.forEach(l -> l.trackStopped(cancelled));
	}

	public void notifyPlaylistInsertedListener(Playlist playlist) {
		playlistInsertedListeners.forEach(l -> l.playlistInserted(playlist));
	}

	/*
	 * LISTENERS END
	 */

	private Library library;

	private Playlist currentPlaylist;
	private Track currentTrack;

	public static final Minim MINIM = new Minim(new MinimHelper());
	private AudioPlayer currentPlayer = null;

	private Thread checkFinishedThread;

	public MusicPlayer(Library lib) {
		library = lib;
		;
	}

	public void close() {
		stop();
		MINIM.dispose();
	}

	private void finished() {
		notifyTrackStoppedListener(false);
		System.out.println("ended normally");
	}

	private void startFinishedChecker() {
		if (checkFinishedThread != null) {
			checkFinishedThread.interrupt();
		}
		
		currentPlayer.printControls();
		checkFinishedThread = new Thread(new Runnable() {
			public void run() {
				while (!checkFinishedThread.isInterrupted()) {
					//System.out.println("Length: "+currentPlayer.length());
					//System.out.println("Pos: "+currentPlayer.position());
					//System.out.println("Playing: "+currentPlayer.isPlaying());
					if (currentPlayer != null && currentPlayer.position() >= currentPlayer.length() - 20) {
						finished();
						break;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						checkFinishedThread.interrupt();
						e.printStackTrace();
					}
				}
			}
		});
		checkFinishedThread.setDaemon(true);
		checkFinishedThread.start();
	}

	@Override
	public void play() {
		stop();
		if (currentTrack == null) {
			return;
		}
		currentPlayer = MINIM.loadFile(currentTrack.getSoundFile().getPath());
		currentPlayer.play();
		startFinishedChecker();
		notifyTrackStartedListener();
	}

	@Override
	public void stop() {
		if (currentPlayer != null) {
			currentPlayer.close();
			notifyTrackStoppedListener(true);
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
		notifyPlaylistInsertedListener(playlist);
	}

	@Override
	public Track getTrack() {
		return currentTrack;
	}

	public void save() {
		library.save(currentPlaylist);
	}

	public void addTrackToPlaylist(Track track) {
		currentPlaylist.addTrack(track);
		library.save(currentPlaylist);
	}

	public Track getTrackFromFile(File file) {
		return FileManager.getTrackFromFile(file);
	}

	public boolean isPlaying() {
		if (currentPlayer == null) {
			return false;
		}
		return currentPlayer.isPlaying();
	}

	public void cue(int millis) {
		if (currentPlayer != null) {
			currentPlayer.cue(millis);
		}
	}

	public int getPosition() {
		if (currentPlayer == null) {
			return 0;
		}
		return currentPlayer.position();
	}

}
