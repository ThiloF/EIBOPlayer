package business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import business.listeners.PlaylistChangedListener;
import business.listeners.PlaylistInsertedListener;
import business.listeners.TrackPausedListener;
import business.listeners.TrackStartedListener;
import business.listeners.TrackStoppedListener;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MusicPlayer {

	/*
	 * LISTENERS START
	 */

	private List<TrackStartedListener> trackStartedListeners = new ArrayList<>();
	private List<TrackStoppedListener> trackStoppedListeners = new ArrayList<>();
	private List<TrackPausedListener> trackPausedListeners = new ArrayList<>();
	private List<PlaylistInsertedListener> playlistInsertedListeners = new ArrayList<>();
	private List<PlaylistChangedListener> playlistChangedListeners = new ArrayList<>();

	public void addTrackStartedListener(TrackStartedListener listener) {
		trackStartedListeners.add(listener);
	}

	public void addTrackStoppedListener(TrackStoppedListener listener) {
		trackStoppedListeners.add(listener);
	}

	public void addTrackPausedListener(TrackPausedListener listener) {
		trackPausedListeners.add(listener);
	}

	public void addPlaylistInsertedListener(PlaylistInsertedListener listener) {
		playlistInsertedListeners.add(listener);
	}

	public void addPlaylistChangedListener(PlaylistChangedListener listener) {
		playlistChangedListeners.add(listener);
	}

	private void notifyTrackStartedListener() {
		trackStartedListeners.forEach(l -> l.trackStarted());
	}

	private void notifyTrackStoppedListener(boolean cancelled) {
		trackStoppedListeners.forEach(l -> l.trackStopped(cancelled));
	}

	private void notifyTrackPausedListener() {
		trackPausedListeners.forEach(l -> l.trackPaused());
	}

	private void notifyPlaylistInsertedListener(Playlist playlist) {
		playlistInsertedListeners.forEach(l -> l.playlistInserted(playlist));
	}

	private void notifyPlaylistChangedListener() {
		playlistChangedListeners.forEach(l -> l.playlistChanged());
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
	private boolean paused = false;

	public MusicPlayer(Library lib) {
		library = lib;
	}

	public void close() {
		stop();
		MINIM.dispose();
	}

	private void finished() {
		currentPlayer.pause();
		notifyTrackStoppedListener(false);
		System.out.println("song ended");
	}

	/**
	 * FIXME Diese Funktion ist ein furchtbarer Hack. Mimim ist toll, um Sachen
	 * abzuspielen, aber es kann einem nicht mitteilen, wann ein Lied vorbei
	 * ist. Noch schlimmer: Nachdem ein Lied bis zum Ende gelaufen ist, bleibt
	 * es zwischen 1 und so 60 ms vor dem Ende händen und setzt isPlaying nie
	 * auf false. Dies ist die beste Lösung, die ich hingekriegt habe, um beim
	 * Liedende ein Event auszulösen...
	 */
	private void startFinishedChecker() {
		if (checkFinishedThread != null) {
			checkFinishedThread.interrupt();
		}

		checkFinishedThread = new Thread(new Runnable() {
			public void run() {
				while (!checkFinishedThread.isInterrupted()) {
					// System.out.println("Length: "+currentPlayer.length());
					// System.out.println("Pos: "+currentPlayer.position());
					// System.out.println("Playing: "+currentPlayer.isPlaying());
					if (currentPlayer != null && currentPlayer.position() >= currentPlayer.length() - 60) {
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

	/**
	 * Spielt den aktuellen Track ab. Falls pausiert, geht es an entsprechender
	 * Stelle weiter.
	 */
	public void play() {
		if (currentTrack == null) {
			return;
		}
		if (currentPlayer == null || currentPlayer.isPlaying()) {
			stop();
		}
		if (currentPlayer == null || (!currentPlayer.isPlaying() && !paused)) {
			currentPlayer = MINIM.loadFile(currentTrack.getSoundFile().getPath());
		}
		currentPlayer.play();
		paused = false;
		startFinishedChecker();
		notifyTrackStartedListener();
	}

	/**
	 * Stoppt den aktuellen Track
	 */
	public void stop() {
		if (currentPlayer != null) {
			// Da Minim's dumme isPlaying() selbst nach close() weiterhin true liefert,
			// wird hier zuerst pausiert.
			currentPlayer.pause();
			currentPlayer.close();
			notifyTrackStoppedListener(true);
			checkFinishedThread.interrupt();
			paused = false;
		}
	}

	/**
	 * Stoppt den aktuellen Track und spielt den nächsten in der Liste ab
	 */
	public void skip() {
		selectTrackNumber(getTrackNumber() + 1);
		play();
	}

	/**
	 * Stoppt den aktuellen Track und spielt den vorherigen in der Liste ab
	 */
	public void skipBack() {
		selectTrackNumber(getTrackNumber() - 1);
		play();
	}

	/**
	 * @return aktuell ausgewählte Playlist
	 */
	public Playlist getPlaylist() {
		return currentPlaylist;
	}

	/**
	 * @return Liste der geladenen Playlists
	 */
	public List<Playlist> getPlaylists() {
		return library.getPlaylists();
	}

	/**
	 * Pausiert die Wiedergabe. Kann mit play() fortgesetzt werden.
	 */
	public void pause() {
		if (!paused) {
			currentPlayer.pause();
			paused = true;
			notifyTrackPausedListener();
		}
	}

	/**
	 * Wählt einen neuen Track aus.
	 * 
	 * @param track
	 *            der neue Track
	 */
	public void selectTrack(Track track) {
		currentTrack = track;
	}

	/**
	 * Wählt einen neuen Track anhand der Nummerierung aus.
	 * 
	 * @param num
	 *            Track-Nummer
	 */
	public void selectTrackNumber(int num) {
		int length = currentPlaylist.getTracks().size();
		num = (num + length) % length;
		selectTrack(currentPlaylist.getTrack(num));
	}

	/**
	 * Wählt eine neue Playlist aus
	 * 
	 * @param playlist
	 *            neue Playlist
	 */
	public void selectPlaylist(Playlist playlist) {
		System.out.println("selecting " + playlist + " when current is " + currentPlaylist);
		currentPlaylist = playlist;
		notifyPlaylistInsertedListener(playlist);
		System.out.println("finished notifying");
	}

	/**
	 * Wählt eine neue Playlist anhand der Nummerierung aus
	 * 
	 * @param num
	 *            Playlist-Nummer
	 */
	public void selectPlaylistNumber(int num) {
		int length = library.getPlaylists().size();
		num = (num + length) % length;
		selectPlaylist(library.getPlaylists().get(num));
	}

	/**
	 * @return aktuell ausgewählter Track
	 */
	public Track getTrack() {
		return currentTrack;
	}

	/**
	 * forciert, dass die aktuelle Playlist gespeichert wird
	 */
	public void save() {
		library.save(currentPlaylist);
	}

	/**
	 * Fügt der aktuell ausgewählten Playlist einen Track hinzu
	 * 
	 * @param track
	 *            hinzuzufügender Track
	 */
	public void addTrackToPlaylist(Track track) {
		currentPlaylist.addTrack(track);
		notifyPlaylistChangedListener();
		library.save(currentPlaylist);
	}

	/**
	 * Gibt an FileManager's Funktion weiter, welche eine Datei zu einem
	 * Track-Objekt umarbeitet
	 * 
	 * @param file
	 *            Musikdatei
	 * @return erstelltes Track-Objekt
	 */
	public Track getTrackFromFile(File file) {
		return FileManager.getTrackFromFile(file);
	}

	/**
	 * @return true, wenn ein Track gerade abgespielt wird
	 */
	public boolean isPlaying() {
		if (currentPlayer == null) {
			return false;
		}
		return currentPlayer.isPlaying();
	}

	/**
	 * Springt im aktuell ausgewählten Track an eine bestimmte Stelle
	 */
	public void cue(int millis) {
		if (currentPlayer != null) {
			currentPlayer.cue(millis);
		}
	}

	/**
	 * @return Stelle, an der sich der momentane Track befindet
	 */
	public int getPosition() {
		if (currentPlayer == null) {
			return 0;
		}
		return currentPlayer.position();
	}

	/**
	 * Entfernt einen Track aus der aktuellen Playlist anhand der Nummerierung
	 * 
	 * @param index
	 *            Track-Nummer
	 */
	public void removeTrack(int index) {
		library.removeTrackFromPlaylist(currentPlaylist, index);
	}

	/**
	 * @return Nummer des Tracks, der gerade abgespielt wird
	 */
	public int getTrackNumber() {
		return currentPlaylist.getTracks().indexOf(currentTrack);
	}

	/**
	 * Fügt eine neue Playlist hinzu
	 * 
	 * @param playlist
	 *            neue Playlist
	 */
	public void addPlaylist(Playlist playlist) {
		library.add(playlist);
		FileManager.writePlaylistToM3U(playlist);
		notifyPlaylistChangedListener();
	}

}
