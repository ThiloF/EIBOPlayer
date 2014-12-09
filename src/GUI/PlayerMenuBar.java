package GUI;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Business.FileManager;
import Business.MusicPlayer;
import Business.Track;


public class PlayerMenuBar extends JMenuBar {

	private TrackFileChooser tfc = new TrackFileChooser(true);
	private MusicPlayer mp3;
	private FileManager m3um;
	private PlayerGui maingui;
	
	
	public PlayerMenuBar(MusicPlayer mp3, PlayerGui pg) {
		// TODO Auto-generated constructor stub
		this.mp3 = mp3;
		this.m3um = new FileManager();
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

						mp3.getPlaylist().addTrack(new Track(f));
						maingui.getPlayLisPanel().updateTracklist();
						m3um.writePlayListToM3U(mp3.getPlaylist());
						
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
		
		playList.add(new AbstractAction("Track aus ausgewählter Playlist löschen") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		
		add(fileMenu);
		add(playList);
		add(computerSaysNo);

	}
	

	
}
