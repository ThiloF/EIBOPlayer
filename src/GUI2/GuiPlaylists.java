package GUI2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.MusicPlayer;
import Business.Playlist;
import Business.Track;

public class GuiPlaylists extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;
	
	private JList<String> choosePlayList;
	private JList<String> chooseTrack;
	private DefaultListModel<String> playListTitle;
	private DefaultListModel<String> trackTitle;
	private JPanel contentCenter;
	
	public GuiPlaylists(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}
	
	private void init() {
		
		setBackground(Color.blue);
		
		playListTitle = new DefaultListModel<String>();
		fillPlayListTitle();
		choosePlayList = new JList<String>(playListTitle);
		trackTitle = new DefaultListModel<String>();
		chooseTrack = new JList<String>(trackTitle);

		choosePlayList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				// actPlaylist =
				// playList.get(playListTitle.get(choosePlayList.getSelectedIndex()));
				player.selectPlaylist(player.getLibrary().getPlaylists().get(choosePlayList.getSelectedIndex()));

				fillTrackList();

			}
		});

		chooseTrack.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting()) {
					return;
				}
				// mp3.setActTrack(actPlaylist.getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
				if (chooseTrack.getSelectedIndex() >= 0) {
					player.selectTrack(player.getPlaylist().getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
				}

			}
		});

		chooseTrack.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE) {
					System.out.println("DELETE!");
					player.getLibrary().removeTrackFromPlaylist(player.getPlaylist(), player.getTrack());
					
					updateTracklist();
				}
			}
		});

		setSize(200, 200);
		setLayout(new GridLayout(1, 2, 10, 10));
		add(new JScrollPane(choosePlayList));
		add(new JScrollPane(chooseTrack));
	}

	/**
	 * Laed die Playlisten in das dafuer zuständige Listmodel (PlaylistTitle).
	 * 
	 */

	private void fillPlayListTitle() {

		for (Playlist pl : player.getLibrary().getPlaylists()) {
			playListTitle.addElement(pl.getTitle());
		}

	}

	/**
	 * Laed die Track in das dafuer zuständige Listmodel (Tracktitle)
	 * 
	 */

	private void fillTrackList() {

		trackTitle.clear();

		for (Track t : player.getPlaylist().getTracks()) {
			trackTitle.addElement(t.getTitle());
		}
	}

	public void updatePlayLists() {
		playListTitle.clear();
		fillPlayListTitle();
	}

	public void updateTracklist() {
		trackTitle.clear();
		fillTrackList();
	}
	
}
