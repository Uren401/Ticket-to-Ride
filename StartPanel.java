
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class StartPanel extends Panel implements MouseListener {
	private BufferedImage startScreen, gameButton, creditsButton;
	
	public StartPanel(Frame f) {
		super(f);
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(startScreen, -20, -30, 1600, 1195, null);

		g.drawImage(Helper.applyOpacity(gameButton, 0.9f), 570,460, 500, 200, null);
		g.drawImage(Helper.applyOpacity(creditsButton, 0.9f), 570, 760, 500, 200, null);
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (x >= 570 && x < 1070 && y >= 460 && y < 660) {
			getFrame().toGame();
		}
		if (x >= 570 && x <= 1070 && y >= 760 && y < 960) {
		}
	}
	
	public void loadImages() {
		try {
			startScreen = ImageIO.read(Panel.class.getResource("/Images/startScreen.png"));
			gameButton = ImageIO.read(Panel.class.getResource("/Images/gameButton.png"));
			creditsButton = ImageIO.read(Panel.class.getResource("/Images/creditsButton.png"));
		} catch (Exception E) {
			System.out.println("FIRE IN THE Start!");
			return;
		}
	}
}
