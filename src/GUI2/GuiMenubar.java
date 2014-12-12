package GUI2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Business.MusicPlayer;
import Business.Track;

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

		setBackground(Color.GREEN);
		
		JMenu fileMenu = new JMenu("Datei");
		JMenu playlistMenu = new JMenu("Playlist");
		JMenu computerSaysNo = new JMenu("?");

		fileMenu.add(new AbstractAction("öffnen") {

			private static final long serialVersionUID = -5870338903689223572L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfc.showOpenDialog(null);
				System.out.println(tfc.getSelectedFile().getName());

			}
		});

		playlistMenu.add(new AbstractAction("Track in aktuelle Playlist laden") {

			private static final long serialVersionUID = 6423693911551664083L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfc.showOpenDialog(null);
				File[] newTracks = tfc.getSelectedFiles();
				for (File f : newTracks) {

					Track track = player.getTrackFromFile(f);
					if (track != null) {
						player.getPlaylist().addTrack(track);
						player.save();
						guiMain.guiTracklist.updateTracklist();
					}
				}
			}

		});

		playlistMenu.add(new AbstractAction("Neue Playlist erstellen") {

			private static final long serialVersionUID = 7445888020658710163L;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				PlaylistNameDialog plnd = new PlaylistNameDialog(guiMain, player);

			}
		});

		playlistMenu.add(new AbstractAction("Track aus ausgewählter Playlist löschen") {

			private static final long serialVersionUID = -6761692872032440359L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		add(fileMenu);
		add(playlistMenu);
		add(computerSaysNo);

	}

}
