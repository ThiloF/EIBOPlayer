package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.MusicPlayer;
import business.Playlist;
import business.Track;

/**
 * Gui-Komponente die das Prompt darstellt, welches beim erstellen einer neuen Playlist nach dem Namen fragt.
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class PlaylistNameDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private JTextField name;
	private JButton speichern;

	/**
	 * Erstellt eine Dialogbox mit Eingabefeld für einen Playlistnamen
	 * @param guiMain
	 * @param player
	 */
	public PlaylistNameDialog(GuiMain guiMain, MusicPlayer player) {
		this.player = player;
		name = new JTextField();
		speichern = new JButton("speichern");
		this.guiMain = guiMain;

		speichern.addActionListener(e -> {
			createNewPlayList();
			dispose();
		});

		this.setTitle("Titel der Playlist eingeben");
		this.setSize(new Dimension(300, 100));
		this.setModal(true);
		this.setLayout(new GridLayout(3, 1));
		this.add(new JLabel("Bitte geben Sie den Titel der Playlist ein"));
		this.add(name);
		this.add(speichern);

		this.setVisible(true);
	}

	/**
	 * Fügt eine neue Playlist der Bibliothek hinzu
	 */
	private void createNewPlayList() {

		String listname = name.getText();
		Playlist playlist = new Playlist(listname, new ArrayList<Track>());
		player.addPlaylist(playlist);

	}

}
