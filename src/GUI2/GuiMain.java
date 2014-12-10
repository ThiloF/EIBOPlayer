package GUI2;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Business.MusicPlayer;

public class GuiMain {

	private JFrame frame;
	private MusicPlayer player;
	
	public GuiNorth guiNorth;
	public GuiControl guiControl;
	public GuiTracklist guiTracklist;
	public GuiTrackinfo guiTrackinfo;
	
	public GuiMain(MusicPlayer player) {
		this.player = player;
		init();
	}
	
	public void init() {
		
		guiNorth = new GuiNorth(this, player);
		guiControl = new GuiControl(this, player);
		guiTracklist = new GuiTracklist(this, player);
		guiTrackinfo = new GuiTrackinfo(this, player);
		
		frame = new JFrame("MP3 Player");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(guiNorth, BorderLayout.NORTH);
		frame.add(guiControl, BorderLayout.SOUTH);
		frame.add(guiTracklist, BorderLayout.WEST);
		frame.add(guiTrackinfo, BorderLayout.CENTER);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				player.close();
			}
		});
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
