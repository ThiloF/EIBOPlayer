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

	private JButton playpause, stop, skip, skipback;
	private JSlider progressSlider;

	private boolean skipped = false;

	public GuiControl(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		setBackground(Color.yellow);

		playpause = new JButton();
		stop = new JButton("stop");
		skip = new JButton(">>");
		skipback = new JButton("<<");

		progressSlider = new JSlider();
		progressSlider.setUI(new CustomSliderUI(progressSlider));
		progressSlider.setMinimum(0);

		playpause.addActionListener(e -> {
			if (player.isPlaying()) {
				player.pause();
			} else {
				player.play();
			}
		});
		
		stop.addActionListener(e -> player.stop());

		player.addTrackStartedListener(() -> {
			updateButtonText();
			setProgressMax(player.getTrack().getLength());
		});
		
		player.addTrackStoppedListener(cancelled -> updateButtonText());
		player.addTrackPausedListener(() -> updateButtonText());

		this.setLayout(new GridLayout(0, 3));
		this.add(skipback);
		this.add(playpause);
		this.add(stop);
		this.add(skip);
		this.add(progressSlider);

		Timer progressTimer = new Timer(200, e -> {
			if (!progressSlider.getValueIsAdjusting()) {
				updateProgress(player.getPosition());
			}
		});
		progressTimer.start();

		progressSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (skipped) {
					skipped = false;
					return;
				}
				if (progressSlider.getValueIsAdjusting()) {
					return;
				}
				System.out.println("Skipping to " + progressSlider.getValue());
				player.cue(progressSlider.getValue());
			}
		});

		updateButtonText();
	}

	private void setProgressMax(int millis) {
		skipped = true;
		progressSlider.setMaximum(millis);
	}

	private void updateProgress(int millis) {
		skipped = true;
		progressSlider.setValue(millis);
	}

	private void updateButtonText() {
		System.out.println("update");
		if (player.isPlaying()) {
			playpause.setText("Pause");
		} else {
			playpause.setText("Play");
		}
	}

}
