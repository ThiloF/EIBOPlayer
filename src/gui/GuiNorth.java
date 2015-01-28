package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import business.MusicPlayer;

/**
 * Gui-Komponente, die den oberen Bereich des Players darstellt und Menüleiste + Playlists beinhaltet
 * 
 * @author fkoen001
 *
 */
public class GuiNorth extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;
	public GuiMenubar guiMenubar;
	public GuiPlaylists guiPlaylists;

	public GuiNorth(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {
		setLayout(new BorderLayout());

		guiMenubar = new GuiMenubar(guiMain, player);
		guiPlaylists = new GuiPlaylists(guiMain, player);

		add(guiMenubar, BorderLayout.NORTH);
		add(guiPlaylists, BorderLayout.CENTER);
	}

}
