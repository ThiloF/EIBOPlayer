package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Business.MP3Player;
import Business.Playlist;
import Business.Track;

public class PlayerGui extends JFrame {

	private JButton playStop;


	private MP3Player mp3;
	private PlayerMenuBar pmb;
	private JPanel southPanel;
	private JTextField titleDuration;
	
	public PlayerGui(MP3Player mp) {
		super("Player");
		mp3 = mp;
		// choosePlayList = new JComboBox();
		// chooseTrack = new JComboBox();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
		init();
	}
	
	

	private void init() {

		playStop = new JButton("play");
	    final PlayListPanel plp = new PlayListPanel(mp3);
	    
	    southPanel = new JPanel();
	    titleDuration = new JTextField();
	    titleDuration.setPreferredSize(new Dimension(200, 25));
	    
	    
		pmb = new PlayerMenuBar(mp3);
		
		

		
		playStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// playStop.setText("pause");
				// mp3.play(actTitle.getSoundFile());

				// playStop.setText("stop");
				//playStop.setText("stop");
				
				new Thread() {
					public void run() {
						if (!mp3.isPlaying()) {
						//	this.setName(plp.getTrack().getTitle());
						
							playStop.setText("stop");
						} else {
							mp3.stop();
							playStop.setText("play");
						}
					}
				}.start();

			}
		});
		
		southPanel.add(playStop);
		southPanel.add(titleDuration);
		
		//Container con = getContentPane();
		setLayout(new BorderLayout());
		add(pmb, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(plp, BorderLayout.CENTER);
		

		// showPlayList();

	}



}
