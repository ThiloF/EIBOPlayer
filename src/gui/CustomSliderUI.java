package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class CustomSliderUI extends BasicSliderUI implements MouseListener {

	public CustomSliderUI(JSlider b) {
		super(b);
		b.addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		slider.setValue(valueForXPosition(e.getX()));
		trackListener.mousePressed(e);
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
