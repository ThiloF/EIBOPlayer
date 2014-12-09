package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Business.MusicPlayer;

public class PlayerGui extends JFrame {

	private static final long serialVersionUID = 5732432854906666184L;

	private JButton playStop;

	private MusicPlayer mp3;
	private PlayerMenuBar pmb;
	private JPanel southPanel;
	private JTextField titleDuration;
	private final PlayListPanel plp;
	
	public PlayerGui(MusicPlayer mp) {
		super("Player");
		mp3 = mp;
		plp = new PlayListPanel(mp);
		// choosePlayList = new JComboBox();
		// chooseTrack = new JComboBox();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		init();
		setVisible(true);
	}
	
	public PlayListPanel getPlayLisPanel(){
		return plp;
	}

	private void init() {

		playStop = new JButton("play");
	   
	    
	    southPanel = new JPanel();
	    titleDuration = new JTextField();
	    titleDuration.setPreferredSize(new Dimension(200, 25));
	    
	    
		pmb = new PlayerMenuBar(mp3,this);
		
		playStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mp3.play();
			}
		});
		
		southPanel.add(playStop);
		southPanel.add(titleDuration);
		
		//Container con = getContentPane();
		setLayout(new BorderLayout());
		add(pmb, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(plp, BorderLayout.CENTER);
		
		pack();
				
		// showPlayList();

	}



}
