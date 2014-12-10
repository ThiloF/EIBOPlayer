package GUI;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Business.MusicPlayer;
import GUI2.TrackFileChooser;


public class PlayerMenuBar extends JMenuBar {

	private static final long serialVersionUID = 2344605988208830109L;
	private TrackFileChooser tfc = new TrackFileChooser(true);
	private MusicPlayer mp3;
	private PlayerGui maingui;
	
	
	public PlayerMenuBar(MusicPlayer mp3, PlayerGui pg) {
		this.mp3 = mp3;
		maingui = pg;
		init();
		
	}
	
	private void init(){
		
		JMenu fileMenu = new JMenu("Datei");
		JMenu playlistMenu = new JMenu("Playlist");
		JMenu computerSaysNo = new JMenu("?");
		
		
		fileMenu.add(new AbstractAction("öffnen") {
			
			private static final long serialVersionUID = -5870338903689223572L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfc.showOpenDialog(null);
				System.out.println(tfc.getSelectedFile().getName());
				
			}
		});
		
		playlistMenu.add(new AbstractAction("Track in aktuelle Playlist laden") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 6423693911551664083L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfc.showOpenDialog(null);
				File[] newTracks = tfc.getSelectedFiles();
					for(File f: newTracks){

						//mp3.addTrackToPlaylist(track);
						//mp3.getPlaylist().addTrack(new Track(f));
						
						maingui.getPlayLisPanel().updateTracklist();
						
					}
				}
				
			
		});
		
		playlistMenu.add(new AbstractAction("Neue Playlist erstellen") {

			private static final long serialVersionUID = 7445888020658710163L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				PLayListNameDialog plnd = new PLayListNameDialog(mp3, maingui);
				
			}
		});
		
		playlistMenu.add(new AbstractAction("Track aus ausgewählter Playlist löschen") {
			
			private static final long serialVersionUID = -6761692872032440359L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		
		add(fileMenu);
		add(playlistMenu);
		add(computerSaysNo);

	}
	

	
}
