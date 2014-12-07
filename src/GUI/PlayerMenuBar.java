package GUI;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Business.M3UManager;
import Business.MP3Player;
import Business.PlayListManager;
import Business.Track;


public class PlayerMenuBar extends JMenuBar {

	private TrackFileChooser tfc = new TrackFileChooser(true);
	private PlayListManager plm = new PlayListManager();
	private MP3Player mp3;
	private M3UManager m3um;
	private PlayerGui maingui;
	
	
	public PlayerMenuBar(MP3Player mp3, PlayerGui pg) {
		// TODO Auto-generated constructor stub
		this.mp3 = mp3;
		this.m3um = new M3UManager();
		maingui = pg;
		init();
		
	}
	
	private void init(){
		
		JMenu fileMenu = new JMenu("Datei");
		JMenu playList = new JMenu("Playlist");
		JMenu computerSaysNo = new JMenu("?");
		
		
		fileMenu.add(new AbstractAction("öffnen") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfc.showOpenDialog(null);
				System.out.println(tfc.getSelectedFile().getName());
				
			}
		});
		
		playList.add(new AbstractAction("Track in aktuelle Playlist laden") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfc.showOpenDialog(null);
				File[] newTracks = tfc.getSelectedFiles();
					for(File f: newTracks){

						mp3.getActPlaylist().addTrack(new Track(f));
						maingui.getPLayLisPanel().updateTracklist();
						m3um.writePlayList(mp3.getActPlaylist());
						
					}
				}
				
			
		});
		
		playList.add(new AbstractAction("Neue Playlist erstellen") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PLayListNameDialog plnd = new PLayListNameDialog(mp3, maingui);
				
			}
		});
		
		
		add(fileMenu);
		add(playList);
		add(computerSaysNo);

	}
	

	
}
