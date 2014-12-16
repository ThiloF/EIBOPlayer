package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import business.MusicPlayer;

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
		
		setLayout(new GridLayout(2, 0));
		
		player.addTrackStartedListener(() -> reload());
		
	}
	
	private void reload() {
		removeAll();
		add(new JLabel(player.getTrack().getTitle()));
	}
	
}