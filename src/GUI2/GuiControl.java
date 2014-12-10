package GUI2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Business.MusicPlayer;

public class GuiControl extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;
	
	private JButton playstop;
	private JButton skip;
	private JButton skipback;
	
	public GuiControl(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}
	
	private void init() {
		
		setBackground(Color.yellow);
		
		playstop = new JButton("Play");
		skip = new JButton(">>");
		skipback = new JButton("<<");
		
		playstop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				player.play();
			}
		});
		
		player.addPropertyChangeListener(new PropertyChangeListener() {
			
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
