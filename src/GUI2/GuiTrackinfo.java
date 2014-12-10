package GUI2;

import java.awt.Color;

import javax.swing.JPanel;

import Business.MusicPlayer;

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
		setBackground(Color.cyan);
	}
	
}
