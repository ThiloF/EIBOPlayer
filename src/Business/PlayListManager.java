package Business;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import Business.Track;

public class PlayListManager {

	private HashMap<String, Playlist> playLists = new HashMap<String, Playlist>();

	
	public PlayListManager(){
		loadPlayList();
	}
	
	
	
	
	public HashMap<String, Playlist> getPlayLists() {
		return playLists;
	}




	public Playlist getPlayList(String name) {
		return playLists.get(name);
	}

	public Track getTrack(Playlist pl, String title) {

		ArrayList<Track> tracks = pl.getList();

		for (Track t : tracks) {
			if (t.getTitle().equals(title)) {
				return t;
			}
		}
		return null;
	}

	private void loadPlayList() {
       ArrayList<File> m3uFiles = M3UManager.getM3Us("");
       ArrayList<Track> tracks;
       Playlist pl;
       String[] title;
       for(File f: m3uFiles){
    	  title = f.getName().split(Pattern.quote("."));
    	   pl = new Playlist(title[0], M3UManager.getTracksfromM3U(f));
    	   
    	   playLists.put(pl.getTitle(), pl);
    	   
       }
       
	}
	
	
	public void print(){
		ArrayList<Track> tracks;
		for(Playlist p: playLists.values()){
			System.out.println(p.getTitle());
			tracks = p.getList();
			for(Track t: tracks){
				System.out.println(t.getSoundFile());
			}
		}
	}
	

}
