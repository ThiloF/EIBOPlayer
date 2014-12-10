package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Business.MusicPlayer;

public class ControlPanel extends JPanel{

	private MusicPlayer mplayer;
	private JButton playstop;
	private JButton skip;
	private JButton skipback;
	
	
	
	public ControlPanel(MusicPlayer mp){
		mplayer = mp;	
		init();
	}
	
	private void init(){
		
		playstop = new JButton("Play");
		skip = new JButton(">>");
		skipback = new JButton("<<");
		
		playstop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mplayer.play();
			}
		});
		
		mplayer.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				playstop.setText((String) evt.getNewValue());
			}
		});
		
		this.setLayout(new GridLayout(0,3));
		this.add(skipback);
		this.add(playstop);
		this.add(skip);
	}
	
}
