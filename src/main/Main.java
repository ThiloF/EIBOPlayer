package main;

import business.Library;
import business.MusicPlayer;
import gui.GuiMain;

/**
 * Einstiegsklasse, die die Main-Funktion beinhaltet. Startet das Haupt-Swingfenster
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class Main {

	public static void main(String[] args) {

		Library library = new Library();
		MusicPlayer mp = new MusicPlayer(library);

		new GuiMain(mp);

	}

}
