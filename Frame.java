
import javax.swing.*;

public class Frame extends JFrame {
	private static final int WIDTH = 1550, HEIGHT = 1100;
	private Panel[] panels = new Panel[3];
	
	public Frame(String framename) {
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

        panels[0] = new StartPanel(this);
		panels[1] = new GamePanel(this);
		panels[2] = new EndPanel(this);

		add(panels[0]);
		setVisible(true);
	}

	public void toGame() {
		remove(panels[0]);
		add(panels[1]);
		revalidate();
		repaint();
	}

	public void toEnd() {
		remove(panels[1]);
		add(panels[2]);
		revalidate();
		repaint();
	}
}
