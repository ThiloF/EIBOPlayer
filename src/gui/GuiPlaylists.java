package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import business.MusicPlayer;
import business.Playlist;

public class GuiPlaylists extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private JScrollPane playlistScrollPane;
	private JPanel playlistPanel;

	public GuiPlaylists(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		setLayout(new GridLayout(1, 1));

		playlistPanel = new JPanel();
		playlistPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Dimension size = new Dimension(1000,160);

		playlistScrollPane = new JScrollPane(playlistPanel);
		playlistScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		playlistScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// list.setMinimumSize(size);
		// list.setPreferredSize(size);
		add(playlistScrollPane);

		updatePlaylists();

		player.addPlaylistInsertedListener(playlist -> updatePlaylists());

	}

	/**
	 * Laed die Playlisten in das dafuer zuständige Listmodel (PlaylistTitle).
	 * 
	 */

	public void updatePlaylists() {
		System.out.println("updating");
		playlistPanel.removeAll();
		for (Playlist playlist : player.getLibrary().getPlaylists()) {
			final JButton pButton = new JButton();
			pButton.setIcon(new ImageIcon(playlist.getCoverImage()));
			pButton.setText(playlist.getTitle());
			pButton.addActionListener(e -> player.selectPlaylist(playlist));
			pButton.setHorizontalTextPosition(JButton.CENTER);
			pButton.setVerticalTextPosition(JButton.BOTTOM);
			if (player.getPlaylist() == playlist) {
				pButton.setBackground(Color.GREEN);
			}
			playlistPanel.add(pButton);
		}
	}

}
