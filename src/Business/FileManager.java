package Business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

	/**
	 * Diese Methode writePlayList bekommt als Parameter eine Playlist
	 * übergeben und speichert diese als erweiterte M3U
	 * 
	 * @param pl
	 */
	public static void writePlayListToM3U(Playlist pl) {
		try (PrintWriter write = new PrintWriter(new FileWriter(pl.getTitle() + ".m3u"))) {
			ArrayList<Track> tracks = pl.getList();
			write.println("#EXTM3U");
			for (Track tr : tracks) {
				write.println("#EXTINF:" + tr.getLength() + "," + tr.getBand() + "-" + tr.getTitle());
				write.println(tr.getSoundFile());
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
	static ArrayList<File> getM3Us(String p) {
		ArrayList<File> m3uFiles = new ArrayList<File>();
		Path dirpath = Paths.get(p);

		try (DirectoryStream<Path> dirstream = Files.newDirectoryStream(dirpath)) {
			for (Path path : dirstream) {
				if (path.toString().toLowerCase().endsWith(".m3u")) {
					m3uFiles.add(new File(path.toString()));
				}
			}
		} catch (IOException ioe) {
			System.out.println("Fehler in Ordner");
		}
		return m3uFiles;
	}

	
	public static Playlist getPlaylistFromM3U(File f) {
		if (!f.exists() || f.isDirectory()) return null;
		
		
		return null;
	}
	
	
	/**
	 * Diese Methode entnimmt aus einer M3Ufile die einzelnen Tracks
	 * 
	 * 
	 * @param f
	 * @return
	 */
	static ArrayList<Track> getTracksfromM3U(File f) {
		String title = null;
		String interpret;
		String path;
		int length = -1;
		ArrayList<Track> tracks = new ArrayList<Track>();
		// String tmp;

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

			String tmp;
			while ((tmp = reader.readLine()) != null) {

				try {
					if (tmp.matches("^#EXTM3U$")) {
						continue;
					}
					Matcher m = Pattern.compile("^#EXTINF:([0-9]*),(.*?)$").matcher(tmp);
					if (m.matches()) {
						length = Integer.parseInt(m.group(1));
						title = m.group(2);
						tracks.add(new Track(title, length, "test", new File(reader.readLine())));
					} else {
						tracks.add(new Track("no title", -1, "test", new File(tmp)));

					}
					// tmp = reader.readLine();
					// path = tmp;
				} catch (Exception ex) {
					System.out.println("Der Fehler lautet: " + tmp);
					ex.printStackTrace();
				}
			}

		} catch (IOException ioe) {
			System.out.println("Fehler bei lesen der Tracks");
		}
		return tracks;
	}

}
