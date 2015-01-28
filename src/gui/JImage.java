package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Eigenes Pseudo-Swing-Element. Wird als Bild verwendet.
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class JImage extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image = null;

	public JImage(File file) {
		this(getImage(file));
	}

	public JImage(BufferedImage image) {
		this.image = image;
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}

	public static BufferedImage getImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}

}
