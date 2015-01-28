package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import business.MusicPlayer;

/**
 * Gui-Komponente, die die Steuerleiste darstellt.
 * @author fkoen001
 *
 */
public class GuiControl extends JPanel {

	private static final long serialVersionUID = 1L;

	private GuiMain guiMain;
	private MusicPlayer player;

	private JButton playpause, stop, skip, skipback, autoskipButton;
	private JSlider progressSlider;

	private boolean skipped = false;
	private boolean autoskip = false;

	public GuiControl(GuiMain guiMain, MusicPlayer player) {
		this.guiMain = guiMain;
		this.player = player;
		init();
	}

	private void init() {

		playpause = new JButton();
		stop = new JButton("■");
		skip = new JButton("►►");
		skipback = new JButton("◄◄");
		autoskipButton = new JButton();
		autoskipButton.setFont(new Font("Sans-Serif", Font.BOLD, 20));
		
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

		autoskipButton.addActionListener(e -> {
			autoskip = !autoskip;
			updateAutoskipButton();
		});
		
		player.addTrackStartedListener(() -> {
			updateButtonText();
			setProgressMax(player.getTrack().getLength());
		});

		player.addTrackStoppedListener(cancelled -> {
			updateButtonText();
			if (!cancelled && autoskip) {
				player.skip();
			}
		});
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

		JPanel left = new JPanel();
		JPanel right = new JPanel();
		left.setPreferredSize(new Dimension(100, 30));
		left.setLayout(new GridLayout(1, 1));
		right.setLayout(new GridLayout(1, 1));
		
		this.setLayout(new GridLayout(0, 2));
		
		left.add(skipback);
		left.add(playpause);
		left.add(stop);
		left.add(skip);
		left.add(autoskipButton);
		right.add(progressSlider);

		add(left);
		add(right);
		
		updateButtonText();
		updateAutoskipButton();
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
		if (player.isPlaying()) {
			playpause.setText("▐▐");
		} else {
			playpause.setText("▶");
		}
	}

	private void updateAutoskipButton() {
		if (autoskip) {
			autoskipButton.setText("→");
			autoskipButton.setToolTipText("Autoskip ist an");
		} else {
			autoskipButton.setText("↛");
			autoskipButton.setToolTipText("Autoskip ist aus");
		}
	}
}
