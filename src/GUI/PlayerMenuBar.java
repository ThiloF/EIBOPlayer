package GUI;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Business.PlayListManager;


public class PlayerMenuBar extends JMenuBar {

	private TrackFileChooser tfc = new TrackFileChooser(true);
	private PlayListManager plm = new PlayListManager();
	
	
	public PlayerMenuBar() {
		// TODO Auto-generated constructor stub
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
					System.out.println(" # gewählte Titel " + f.getName());
					}
				}
				
			
		});
		
		playList.add(new AbstractAction("Neue Playlist erstellen") {
			
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
