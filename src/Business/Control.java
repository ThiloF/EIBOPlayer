package Business;
import GUI.PlayerGui;

public class Control {

	public static void main(String[] args) {

		Library lib = new Library();
		lib.print();
		
		MusicPlayer mp = new MusicPlayer(lib);
	
		new PlayerGui(mp);
		
	}

	
	
	
	

		

}
