package GUI2;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.MusicPlayer;
import Business.Playlist;
import Business.Track;

public class GuiTracklist extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;
	
	JList<String> tracklist;
	DefaultListModel<String> tracklistModel = new DefaultListModel<>();
	
	public GuiTracklist(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
		
		player.addPlaylistInsertedListener(p -> updateTracklist(p));
	}
	
	private void init() {
		setBackground(Color.red);
		
		tracklist = new JList<String>(tracklistModel);
		
		tracklist.addListSelectionListener(e -> {
			if (e.getValueIsAdjusting() || tracklist.getSelectedIndex() < 0) {
				return;
			}
			player.selectTrackNumber(tracklist.getSelectedIndex());
		});
		
		tracklist.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tracklist.getSelectedIndex() >= 0 &&e.getKeyChar() == KeyEvent.VK_DELETE) {
					player.removeTrack(tracklist.getSelectedIndex());
					updateTracklist(player.getPlaylist());
				}
			}
		});
		
		add(tracklist);
		
	}
	
	public void updateTracklist() {
		updateTracklist(player.getPlaylist());
	}
	
	public void updateTracklist(Playlist p) {
		tracklistModel.clear();
		for (Track track : p.getTracks()) {
			tracklistModel.addElement(track.getTitle());
		}
	}
	
}
