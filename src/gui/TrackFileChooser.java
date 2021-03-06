package gui;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * Gui-Komponente, welche die Dateiauswahl für das Hinzufügen von Tracks darstellt.
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class TrackFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	private boolean searchMode = false;

	public TrackFileChooser(boolean isDirectorieSearch) {
		this.searchMode = isDirectorieSearch;
		this.setMultiSelectionEnabled(true);
	}

	/**
	 * Gibt zurück, ob der Chooser die Datei akzeptiert
	 */
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
