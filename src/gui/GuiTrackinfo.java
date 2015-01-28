package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.MusicPlayer;

/**
 * Gui-Komponente, die die Trackinformationen darstellt
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class GuiTrackinfo extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	public GuiTrackinfo(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(200, 0));

		player.addTrackStartedListener(() -> reload());

	}

	private void reload() {
		removeAll();

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());

		p1.add(new JImage(player.getPlaylist().getCoverImage()));
		p2.add(new JLabel(player.getTrack().getTitle()));
		add(p1);
		add(p2);

		revalidate();
		repaint();
	}

}
