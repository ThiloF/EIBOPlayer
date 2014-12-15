package GUI2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

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
		stop = new JButton("■");
		skip = new JButton("►►");
		skipback = new JButton("◄◄");

		Dimension size = new Dimension(30, 30);
		playpause.setPreferredSize(size);
		stop.setPreferredSize(size);
		skip.setPreferredSize(size);
		skipback.setPreferredSize(size);

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
		skip.addActionListener(e -> player.skip());
		skipback.addActionListener(e -> player.skipBack());

		player.addTrackStartedListener(() -> {
			updateButtonText();
			setProgressMax(player.getTrack().getLength());
		});

		player.addTrackStoppedListener(cancelled -> updateButtonText());
		player.addTrackPausedListener(() -> updateButtonText());

		progressSlider.addChangeListener(e -> {
			if (skipped) {
				skipped = false;
				return;
			}
			if (!progressSlider.getValueIsAdjusting()) {
				player.cue(progressSlider.getValue());
			}
		});

		Timer progressTimer = new Timer(200, e -> {
			if (!progressSlider.getValueIsAdjusting()) {
				updateProgress(player.getPosition());
			}
		});
		progressTimer.start();

		this.setLayout(new GridLayout(0, 5));
		this.add(skipback);
		this.add(playpause);
		this.add(stop);
		this.add(skip);
		this.add(progressSlider);

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
			playpause.setText("▐▐");
		} else {
			playpause.setText("▶");
		}
	}

}
