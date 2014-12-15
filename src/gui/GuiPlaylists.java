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

		playlistScrollPane = new JScrollPane(playlistPanel);
		playlistScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		playlistScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(playlistScrollPane);

		updatePlaylists();

		player.addPlaylistInsertedListener(playlist -> updatePlaylists());
		player.addPlaylistChangedListener(() -> updatePlaylists());

	}

	/**
	 * Baut das Panel, welches die Playlists beinhaltet, neu auf
	 */
	public void updatePlaylists() {
		System.out.println("updating playlist");
		playlistPanel.removeAll();
		for (final Playlist playlist : player.getPlaylists()) {

			final JButton playlistButton = new JButton();
			playlistButton.setIcon(new ImageIcon(playlist.getCoverImage()));
			playlistButton.setText(playlist.getTitle());
			playlistButton.addActionListener(e -> player.selectPlaylist(playlist));
			playlistButton.setHorizontalTextPosition(JButton.CENTER);
			playlistButton.setVerticalTextPosition(JButton.BOTTOM);

			if (player.getPlaylist() == playlist) {
				playlistButton.setBackground(Color.GREEN);
			}
			playlistPanel.add(playlistButton);
		}
	}

}
