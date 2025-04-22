
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class EndPanel extends Panel {
	private BufferedImage endScreen;
    private Map<String, BufferedImage> stations;
	
	public EndPanel(Frame f) {
		super(f);
	}
	
	public void paint(Graphics g) {
		g.drawImage(endScreen, -20, -30, 1600, 1195, null);

        g.setFont(new Font("Sans Serif", Font.BOLD, 90));
        g.drawImage(stations.get("blue"), 720, 150, 158, 258, null);
        g.drawString("120", 720, 330);
        g.drawImage(stations.get("red"), 280, 330, 158, 258, null);
        g.drawString("88", 280, 510);
        g.drawImage(stations.get("green"), 1160, 330, 158, 258, null);
        g.drawString("72", 1160, 510);
        g.drawImage(stations.get("yellow"), 720, 720, 158, 258, null);
        g.drawString("45", 720, 900);
	}

	public void loadImages() {
        stations = new HashMap<>();
		try {
			endScreen = ImageIO.read(Panel.class.getResource("/Images/endScreen.png"));

            stations.put("red", ImageIO.read(Panel.class.getResource("/Images/Stations/redStation.png")));
			stations.put("blue", ImageIO.read(Panel.class.getResource("/Images/Stations/blueStation.png")));
			stations.put("green", ImageIO.read(Panel.class.getResource("/Images/Stations/greenStation.png")));
			stations.put("yellow", ImageIO.read(Panel.class.getResource("/Images/Stations/yellowStation.png")));
		} catch (Exception E) {
			System.out.println("FIRE IN THE End!");
			return;
		}
	}
}
