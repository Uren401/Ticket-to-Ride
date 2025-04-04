
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel extends Panel implements MouseListener {
	private BufferedImage map;
	private Map<String, BufferedImage> cards;
	private BufferedImage[] tickets;
	// GameState game;
	
	public GamePanel(Frame f) {
		super(f);
		addMouseListener(this);
		// game = new GameState();
	}
	
	public void paint(Graphics g) {
		g.drawImage(map, 0, 0, 1300, 840, null);
		g.drawImage(tickets[0], 1330, 5, 180, 115, null);
		g.drawImage(cards.get("back"), 1330, 120, 180, 115, null);

		String[] deck = {"red", "black", "loco", "orange", "orange"};
		for (int i = 0; i < 5; i++) {
			g.drawImage(cards.get(deck[i]), 1330, 245 + 120 * i, 180, 115, null);
		}

		
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();


	}
	
	public void loadImages() {
		cards = new HashMap<>();
		tickets = new BufferedImage[1];
		try {
			map = ImageIO.read(Panel.class.getResource("/Images/europeMap.png"));
			cards.put("back", ImageIO.read(Panel.class.getResource("/Images/backCard.png")));
			for (String s : Helper.colors) {
				cards.put(s, ImageIO.read(Panel.class.getResource("/Images/Cards/" + s + "Card.png")));
			}
			tickets[0] = ImageIO.read(Panel.class.getResource("/Images/backTicket.png"));
		} catch (Exception E) {
			System.out.println("FIRE IN THE Game!");
			return;
		}
	}
}