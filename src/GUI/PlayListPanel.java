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

	private HashMap<String, Playlist> playList;
	private Track actTitle;
	private Playlist actPlaylist;

	private JList<String> choosePlayList;
	private JList<String> chooseTrack;
	private DefaultListModel<String> playListTitle;
	private DefaultListModel<String> trackTitle;
	private JPanel contentCenter;
	private MP3Player mp3;

	public PlayListPanel(MP3Player mp3) {
		PlayListManager plm = new PlayListManager();
		this.playList = plm.getPlayLists();
		this.mp3 = mp3;
		init();
	}
	
	public Track getTrack(){
		return actTitle;
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

				actPlaylist = playList.get(playListTitle.get(choosePlayList.getSelectedIndex()));

				fillTrackList();

			}
		});

		chooseTrack.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				mp3.setActTrack(actPlaylist.getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
			}
		});

		setSize(200, 200);
		setLayout(new GridLayout(1, 2, 10, 10));
		add(new JScrollPane(choosePlayList));
		add(new JScrollPane(chooseTrack));
	}

	private void fillPlayListTitle() {

		ArrayList<String> title = new ArrayList<String>();

		for (Playlist pl : playList.values()) {
			playListTitle.addElement(pl.getTitle());
		}

	}

	private void fillTrackList() {

		trackTitle.clear();

		for (Track t : actPlaylist.getList()) {
			trackTitle.addElement(t.getTitle());
		}
	}

}
