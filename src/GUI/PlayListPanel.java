package GUI;
import Business.MP3Player;
import Business.PlayListManager;
import Business.Playlist;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.Track;

public class PlayListPanel extends JPanel {

	

	private JList<String> choosePlayList;
	private JList<String> chooseTrack;
	private DefaultListModel<String> playListTitle;
	private DefaultListModel<String> trackTitle;
	private JPanel contentCenter;
	private MP3Player mp3;

	public PlayListPanel(MP3Player mp3) {
		this.mp3 = mp3;
		init();
	}
	

	private void init() {

		playListTitle = new DefaultListModel<String>();
		fillPlayListTitle();
		choosePlayList = new JList<String>(playListTitle);
		trackTitle = new DefaultListModel<String>();
		chooseTrack = new JList<String>(trackTitle);


		choosePlayList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting())
					return;

				//actPlaylist = playList.get(playListTitle.get(choosePlayList.getSelectedIndex()));
				mp3.setActPlaylist(mp3.getPlayLists().get(playListTitle.get(choosePlayList.getSelectedIndex())));

				fillTrackList();

			}
		});

		chooseTrack.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				//mp3.setActTrack(actPlaylist.getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
				mp3.setActTrack(mp3.getActPlaylist().getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
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

		ArrayList<String> title = new ArrayList<String>();

		for (Playlist pl : mp3.getPlayLists().values()) {
			playListTitle.addElement(pl.getTitle());
		}

	}
	
	/**
	 * Laed die Track in das dafuer zuständige Listmodel (Tracktitle)
	 * 
	 */

	private void fillTrackList() {

		trackTitle.clear();

		for (Track t : mp3.getActPlaylist().getList()) {
			trackTitle.addElement(t.getTitle());
		}
	}
	
	public void updatePlayLists(){
		playListTitle.clear();
		fillPlayListTitle();
	}
	
	public void updateTracklist(){
		trackTitle.clear();
		fillTrackList();
	}

}
