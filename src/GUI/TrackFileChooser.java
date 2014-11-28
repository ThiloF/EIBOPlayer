package GUI;
import java.io.File;

import javax.swing.JFileChooser;


public class TrackFileChooser extends JFileChooser {

	
	public boolean accept(File f) {
	      // Auch Unterverzeichnisse anzeigen
	      if (f.isDirectory())
	        return true;
	      return f.getName().toLowerCase().endsWith(".mp3");
	    }

	    public String getDescription() { return "MP3"; }  

	  }
	

