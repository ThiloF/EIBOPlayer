package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import business.MusicPlayer;
import business.Playlist;

/**
 * Gui-Komponente, die die Playlist-Liste darstellt
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
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

		player.addPlaylistInsertedListener(playlist -> updatePlaylists());
		player.addPlaylistChangedListener(() -> updatePlaylists());

		playlistPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (player.getPlaylist() != null && e.getKeyChar() == KeyEvent.VK_DELETE) {
					player.removePlaylist(player.getPlaylist());
					updatePlaylists();
				}
			}
		});

		updatePlaylists();
	}

	/**
	 * Baut das Panel, welches die Playlists beinhaltet, neu auf
	 */
	public void updatePlaylists() {
		playlistPanel.removeAll();
		for (final Playlist playlist : player.getPlaylists()) {

			JButton playlistButton = new JButton();
			playlistButton.setIcon(new ImageIcon(playlist.getCoverImage()));
			playlistButton.setText(playlist.getTitle());
			playlistButton.setHorizontalTextPosition(JButton.CENTER);
			playlistButton.setVerticalTextPosition(JButton.BOTTOM);
			if (player.getPlaylist() == playlist) {
				playlistButton.setBackground(GuiMain.selectionColor);
			}
			playlistButton.addActionListener(e -> player.selectPlaylist(playlist));
			playlistPanel.add(playlistButton);
		}
		revalidate();
		repaint();
	}

}
