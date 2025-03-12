
import java.awt.event.MouseEvent;
import javax.swing.*;

public abstract class Panel extends JPanel {
	private Frame frame;
	
	public Panel(Frame f) {
		frame = f;
		loadImages();
	}

    public Frame getFrame() {
        return frame;
    }

	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void loadImages() {}
}
