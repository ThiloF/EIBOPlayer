package Business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;

public class FileManager {

	private static String playlistsPath = "playlists";
	
	public static Track getTrackFromFile(File file) {
		return getTrackFromFile(file, null);
	}

	public static Track getTrackFromFile(File file, String title) {

		if (!file.exists() || file.isDirectory()) {
			return null;
		}

		AudioPlayer p = MusicPlayer.MINIM.loadFile(file.getPath());
		AudioMetaData meta = p.getMetaData();
		p.close();

		if (title == null || title.isEmpty()) {
			title = meta.title();
		}

		return new Track(title, meta.length(), meta.author(), file);

	}

	/**
	 * Diese Methode writePlayList bekommt als Parameter eine Playlist übergeben
	 * und speichert diese als erweiterte M3U
	 * 
	 * @param pl
	 */
	public static void writePlaylistToM3U(Playlist pl) {
		try (PrintWriter write = new PrintWriter(new FileWriter(playlistsPath + File.separatorChar + pl.getTitle() + ".m3u"))) {
			ArrayList<Track> tracks = pl.getTracks();
			write.println("#EXTM3U");
			for (Track track : tracks) {
				write.println("#EXTINF:" + track.getLength() + "," + track.getBand() + "-" + track.getTitle());
				write.println(track.getSoundFile());
			}
		} catch (IOException ioe) {
			System.out.println("Konnte Datei nicht schreiben");
		}
	}

	/**
	 * Die Methode getM3Us durchsucht ein Verzeichnis nach m3us und gibt diese
	 * in einer ArrayList vom Typ File zurück
	 * 
	 * @param p
	 * @return
	 */
	public static ArrayList<File> getM3Us() {
		ArrayList<File> m3uFiles = new ArrayList<File>();
		Path dirpath = Paths.get(playlistsPath);

		try (DirectoryStream<Path> dirstream = Files.newDirectoryStream(dirpath)) {
			for (Path path : dirstream) {
				if (path.toString().toLowerCase().endsWith(".m3u")) {
					m3uFiles.add(new File(path.toString()));
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return m3uFiles;
	}

	/**
	 * Diese Methode liest eine .m3u Datei und erzeugt eine Playlist
	 * 
	 * @param m3uFile
	 *            Pfad, ab dem nach M3Us gesucht wird
	 * @return Playlist, die aus der M3U gelesen wurde
	 */
	public static Playlist getPlaylistFromM3U(File m3uFile) {
		ArrayList<Track> tracks = new ArrayList<Track>();
		// String tmp;

		boolean isExtM3U = false;
		Matcher m3uMatcher = Pattern.compile("^#EXTINF:[0-9]*,(.*?)$").matcher("");

		String title = "";

		try (Scanner scanner = new Scanner(m3uFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.matches("^#EXTM3U$"))
					isExtM3U = true;
				else if (isExtM3U && m3uMatcher.reset(line).find()) {
					title = m3uMatcher.group(1);
				} else {
					File trackfile = new File(line);
					if (trackfile.exists() && !trackfile.isDirectory()) {
						System.out.println(title);
						tracks.add(getTrackFromFile(trackfile, title));
						title = "";
					} else {
						System.err.println("File not found: " + trackfile);
					}
				}
			}
		} catch (IOException ioe) {
			System.out.println("Fehler bei lesen der Tracks");
		}
		String playlistName = m3uFile.getName();
		playlistName = playlistName.substring(0, playlistName.lastIndexOf('.'));
		return new Playlist(playlistName, tracks);
	}

	/**
	 * Die Methode searchForMP3 durchläuft das Dateisystem ab dem im paramter
	 * angebenen Parameter f
	 * 
	 * @param f
	 * @param mp3list
	 * @return
	 */

	public static ArrayList<File> searchForMP3s(File f) {
		if (f == null || !f.isDirectory()) {
			return null;
		}

		File[] fileArray = f.listFiles();
		ArrayList<File> mp3list = new ArrayList<File>();

		for (File file : fileArray) {
			if (file.isDirectory()) {
				mp3list.addAll(searchForMP3s(file));
			} else if (file.getPath().toString().toLowerCase().endsWith(".mp3")) {
				mp3list.add(file);
			}
		}
		return mp3list;
	}

}
