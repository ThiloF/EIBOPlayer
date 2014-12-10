package GUI2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Business.MusicPlayer;

public class GuiControl extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private JButton playstop;
	private JButton skip;
	private JButton skipback;
	private JSlider progressSlider;

	public GuiControl(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		setBackground(Color.yellow);

		playstop = new JButton();
		skip = new JButton(">>");
		skipback = new JButton("<<");

		progressSlider = new JSlider();
		progressSlider.setUI(new CustomSliderUI(progressSlider));
		progressSlider.setMinimum(0);

		playstop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.play();
			}
		});

		player.addTrackStartedListener(() -> {
			updateButtonText();
			setProgressMax(player.getTrack().getLength());
		});
		player.addTrackStoppedListener(cancelled -> updateButtonText());

		this.setLayout(new GridLayout(0, 3));
		this.add(skipback);
		this.add(playstop);
		this.add(skip);
		this.add(progressSlider);

		Timer progressTimer = new Timer(200, e -> {
			updateProgress(player.getPosition());
		});
		progressTimer.start();
		
		progressSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (progressSlider.getValueIsAdjusting()) {
					return;
				}
				player.skipTo(progressSlider.getValue());
			}
		});

		updateButtonText();
	}

	private void setProgressMax(int millis) {
		progressSlider.setValueIsAdjusting(true);
		progressSlider.setMaximum(millis);
	}

	private void updateProgress(int millis) {
		progressSlider.setValueIsAdjusting(true);
		System.out.println("Progress " + millis);
		progressSlider.setValue(millis);
	}

	private void updateButtonText() {
		if (player.isPlaying()) {
			playstop.setText("Stop");
		} else {
			playstop.setText("Play");
		}
	}

}
