package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Business.M3UManager;
import Business.MP3Player;
import Business.PlayListManager;
import Business.Playlist;
import Business.Track;

public class PLayListNameDialog extends JDialog {

	private JTextField name;
	private JButton speichern;
	private MP3Player mp3;
	private PlayerGui mainGui;
	
	public PLayListNameDialog(MP3Player mp3, PlayerGui pg){
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
		
		PlayListManager plm = mp3.getPlayListManager();
		String listname = name.getText();
		Playlist pl = new Playlist(listname, new ArrayList<Track>());
		plm.addPlayList(listname, pl);
		mainGui.getPLayLisPanel().updatePlayLists();
		M3UManager.writePlayList(pl);
		
		
	}
	
}
