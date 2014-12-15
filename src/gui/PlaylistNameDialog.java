package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.FileManager;
import business.Library;
import business.MusicPlayer;
import business.Playlist;
import business.Track;

public class PlaylistNameDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private JTextField name;
	private JButton speichern;

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
		this.add(new JLabel("Bitte geben Sie den Titel der PLaylist ein"));
		this.add(name);
		this.add(speichern);

		this.setVisible(true);
	}

	private void createNewPlayList() {

		String listname = name.getText();
		Playlist playlist = new Playlist(listname, new ArrayList<Track>());
		player.addPlaylist(playlist);

	}

}
