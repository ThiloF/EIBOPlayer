package business;

import gui.GuiMain;

/**
 * Einstiegsklasse, die die Main-Funktion beinhaltet. Startet das Haupt-Swingfenster
 * 
 * @author fkoen001
 *
 */
public class Main {

	public static void main(String[] args) {

		Library library = new Library();
		MusicPlayer mp = new MusicPlayer(library);

		new GuiMain(mp);

	}

}
