package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Business.FileManager;
import Business.Library;
import Business.MusicPlayer;
import Business.Playlist;
import Business.Track;

public class PLayListNameDialog extends JDialog {

	private JTextField name;
	private JButton speichern;
	private MusicPlayer mp3;
	private PlayerGui mainGui;
	
	public PLayListNameDialog(MusicPlayer mp3, PlayerGui pg){
		this.mp3 = mp3;
		name = new JTextField();
		speichern = new JButton("speichern");
		mainGui = pg;
		
		speichern.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createNewPlayList();
				dispose();
			
			}
		});
		
		this.setTitle("Titel der Playlist eingeben");
		this.setSize(new Dimension(300, 100));
		this.setModal(true);
		this.setLayout(new GridLayout(3, 1));
		this.add(new JLabel("Bitte geben Sie den Titel der PLaylist ein"));
		this.add(name);
		this.add(speichern);
		
		
		this.setVisible(true);
	}
	
	private void createNewPlayList(){
		
		Library library = mp3.getLibrary();
		String listname = name.getText();
		Playlist pl = new Playlist(listname, new ArrayList<Track>());
		library.add(pl);
		mainGui.getPlayLisPanel().updatePlayLists();
		FileManager.writePlayListToM3U(pl);
		
		
	}
	
}
