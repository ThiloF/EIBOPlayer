package Business;
import java.util.ArrayList;
import java.util.Date;

import Business.Track;


public class Playlist {
	private long id;
	private String title;
	private String creationDate; //fragen welches Date wir nehmen sollen
	private String coverFile;
	ArrayList<Track> list = new ArrayList<Track>();
	
	
	
	public Playlist(long id, String title, String creationDate, String coverFile, ArrayList<Track> list) {
		this.id = id;
		this.title = title;
		this.creationDate = creationDate;
		this.coverFile = coverFile;
		this.list = list;
	}
	
	public Playlist(String title, ArrayList<Track> tracks){
		this(0, title, "jetzt", null, tracks);
	}
	
	public Track getTitle(String title){
		for(Track t: list){
			if(t.getTitle().equals(title)){
				return t;
			}
		}
		return null;
	}
	
	
	
	public ArrayList<Track> getList() {
		return list;
	}




	public void setList(ArrayList<Track> list) {
		this.list = list;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public void addTrack(Track tr){
		list.add(tr);
	}
	

	public int numberOfTracks(){
		return list.size();
	}
	
	public Track getTrack(int no){
		for(Track t: list){
			if(no == t.getId()){
				return t;
			}
		}
		return null;
	}
	
	public void printList(){
		for(Track t : list){
			System.out.println("Name: " + t.getTitle());
		}
	}
	
}
