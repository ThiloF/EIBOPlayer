package GUI;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.MusicPlayer;
import Business.Playlist;
import Business.Track;

public class PlayListPanel extends JPanel {

	private static final long serialVersionUID = -788008274752915816L;
	
	private JList<String> choosePlayList;
	private JList<String> chooseTrack;
	private DefaultListModel<String> playListTitle;
	private DefaultListModel<String> trackTitle;
	private JPanel contentCenter;
	private MusicPlayer mp3;
	
   

	public PlayListPanel(MusicPlayer mp3) {
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
				if (e.getValueIsAdjusting())
					return;

				//actPlaylist = playList.get(playListTitle.get(choosePlayList.getSelectedIndex()));
				mp3.selectPlaylist(mp3.getLibrary().getPlaylists().get(choosePlayList.getSelectedIndex()));

				fillTrackList();

			}
		});

		chooseTrack.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				//mp3.setActTrack(actPlaylist.getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
				mp3.selectTrack(mp3.getPlaylist().getTitle(trackTitle.get(chooseTrack.getSelectedIndex())));
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

		for (Playlist pl : mp3.getLibrary().getPlaylists()) {
			playListTitle.addElement(pl.getTitle());
		}

	}
	
	/**
	 * Laed die Track in das dafuer zuständige Listmodel (Tracktitle)
	 * 
	 */

	private void fillTrackList() {

		trackTitle.clear();

		for (Track t : mp3.getPlaylist().getList()) {
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
