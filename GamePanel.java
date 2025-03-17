
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel extends Panel implements MouseListener {
	private BufferedImage map;
	private Map<String, BufferedImage> cards;
	private BufferedImage tickets[];
	
	public GamePanel(Frame f) {
		super(f);
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(map, 0, 0, 1300, 840, null);
		g.drawImage(cards.get("back"), 1325, 25, 210, 135, null);

		String[] deck = {"red", "black", "loco", "orange", "orange"};
		for (int i = 0; i < 5; i++) {
			g.drawImage(cards.get(deck[i]), 1325, 185 + 160 * i, 210, 135, null);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
	}
	
	public void loadImages() {
		cards = new HashMap<>();
		try {
			map = ImageIO.read(Panel.class.getResource("/Images/europeMap.png"));
			cards.put("back", ImageIO.read(Panel.class.getResource("/Images/Cards/backCard.png")));
			cards.put("loco", ImageIO.read(Panel.class.getResource("/Images/Cards/locoCard.png")));
			cards.put("black", ImageIO.read(Panel.class.getResource("/Images/Cards/blackCard.png")));
			cards.put("blue", ImageIO.read(Panel.class.getResource("/Images/Cards/blueCard.png")));
			cards.put("green", ImageIO.read(Panel.class.getResource("/Images/Cards/greenCard.png")));
			cards.put("orange", ImageIO.read(Panel.class.getResource("/Images/Cards/orangeCard.png")));
			cards.put("purple", ImageIO.read(Panel.class.getResource("/Images/Cards/purpleCard.png")));
			cards.put("red", ImageIO.read(Panel.class.getResource("/Images/Cards/redCard.png")));
			cards.put("white", ImageIO.read(Panel.class.getResource("/Images/Cards/whiteCard.png")));
			cards.put("yellow", ImageIO.read(Panel.class.getResource("/Images/Cards/yellowCard.png")));
		} catch (Exception E) {
			System.out.println("FIRE IN THE Game!");
			return;
		}
	}
}
