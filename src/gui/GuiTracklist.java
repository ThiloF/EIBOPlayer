package gui;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import business.MusicPlayer;
import business.Playlist;
import business.Track;

/**
 * Gui-Komponente, die die Trackliste darstellt
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class GuiTracklist extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private JList<String> tracklist;
	private DefaultListModel<String> tracklistModel = new DefaultListModel<>();

	public GuiTracklist(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		tracklist = new JList<String>(tracklistModel);

		Dimension size = new Dimension(400, 300);
		tracklist.setMinimumSize(size);
		tracklist.setPreferredSize(size);
		tracklist.setSelectionBackground(GuiMain.selectionColor);

		tracklist.addListSelectionListener(e -> {
			if (e.getValueIsAdjusting() || tracklist.getSelectedIndex() < 0) {
				return;
			}
			player.selectTrackNumber(tracklist.getSelectedIndex());
		});

		tracklist.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tracklist.getSelectedIndex() >= 0 && e.getKeyChar() == KeyEvent.VK_DELETE) {
					player.removeTrack(tracklist.getSelectedIndex());
					updateTracklist(player.getPlaylist());
				}
			}
		});

		tracklist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// Doppelklick
					player.selectTrackNumber(tracklist.getSelectedIndex());
					player.play();
				}
			}
		});

		player.addPlaylistInsertedListener(p -> updateTracklist(p));
		player.addPlaylistChangedListener(() -> updateTracklist());
		player.addTrackStartedListener(() -> tracklist.setSelectedIndex(player.getTrackNumber()));

		add(tracklist);

	}

	public void updateTracklist() {
		updateTracklist(player.getPlaylist());
	}

	public void updateTracklist(Playlist p) {
		tracklistModel.clear();
		if (p != null) {
			for (Track track : p.getTracks()) {
				tracklistModel.addElement(track.getTitle());
			}
		}
	}

}
