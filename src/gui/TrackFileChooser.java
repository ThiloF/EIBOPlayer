package gui;

import java.io.File;

import javax.swing.JFileChooser;

public class TrackFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;
	
	private boolean searchMode = false;

	public TrackFileChooser(boolean isDirectorieSearch) {
		this.searchMode = isDirectorieSearch;
		this.setMultiSelectionEnabled(true);
	}

	public boolean accept(File f) {
		// Auch Unterverzeichnisse anzeigen
		if (f.isDirectory()) {
			return true;
		}
		return f.getName().toLowerCase().endsWith(".mp3");
	}

	public String getDescription() {
		return "MP3";
	}

}
