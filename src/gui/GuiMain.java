package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import business.MusicPlayer;

/**
 * Haupt-Gui. Stellt das eigentliche Player-Fenster dar und beinhaltet die einzelnen Gui-Komponenten
 * @author fkoen001
 *
 */
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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
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
