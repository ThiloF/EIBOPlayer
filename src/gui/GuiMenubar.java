package gui;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import business.MusicPlayer;
import business.Track;

/**
 * Gui-Komponente, die die Menüleiste darstellt.
 * 
 * @author fkoen001
 *
 */
public class GuiMenubar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private TrackFileChooser tfc = new TrackFileChooser(true);

	public GuiMenubar(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		JMenu fileMenu = new JMenu("Datei");
		JMenu playlistMenu = new JMenu("Playlist");
		JMenu computerSaysNo = new JMenu("?");

		fileMenu.add(new AbstractAction("öffnen") {

			private static final long serialVersionUID = -5870338903689223572L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfc.showOpenDialog(null);
			}
		});

		playlistMenu.add(new AbstractAction("Track in aktuelle Playlist laden") {

			private static final long serialVersionUID = 6423693911551664083L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfc.showOpenDialog(null);
				File[] newTracks = tfc.getSelectedFiles();
				for (File f : newTracks) {

					Track track = player.getTrackFromFile(f);
					if (track != null) {
						player.addTrackToPlaylist(track);
					}
				}
			}

		});

		playlistMenu.add(new AbstractAction("Neue Playlist erstellen") {

			private static final long serialVersionUID = 7445888020658710163L;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				new PlaylistNameDialog(guiMain, player);

			}
		});

		playlistMenu.add(new AbstractAction("Track aus ausgewählter Playlist löschen") {

			private static final long serialVersionUID = -6761692872032440359L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		playlistMenu.add(new AbstractAction("Ausgewählte Playlist löschen") {

			private static final long serialVersionUID = -1665246041829119704L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.getPlaylist() != null) {
					player.removePlaylist(player.getPlaylist());
				}
			}
		});

		add(fileMenu);
		add(playlistMenu);
		add(computerSaysNo);

	}

}
