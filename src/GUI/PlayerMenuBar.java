package GUI;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class PlayerMenuBar extends JMenuBar {

	private TrackFileChooser tfc = new TrackFileChooser();
	
	
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
		
		playList.add(new AbstractAction("Playlist auswählen") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		playList.add(new AbstractAction("Playlist ändern") {
			
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
