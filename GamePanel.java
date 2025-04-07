
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel extends Panel implements MouseListener {
	private BufferedImage map;
	private Map<String, BufferedImage> cards, trains;
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

		paintHand(g);

		g.setFont(new Font("Sans Serif", Font.BOLD, 50));
		g.setColor(Color.GREEN);
		g.drawString("45", 1400, 920);
		g.setColor(Color.RED);
		g.drawString("45", 1270, 920);
		g.setColor(Color.BLUE);
		g.drawString("45", 1140, 920);
		g.setColor(Color.YELLOW);
		g.drawString("45", 1010, 920);

		g.drawImage(tickets[0], 750, 870, 180, 115, null);

		// make jbuttons when
	}

	private void paintHand(Graphics g) {
		HashMap<String, Integer> hand = Helper.getEmptyDeck();
		hand.put("blue", 4);
		hand.put("yellow", 2);
		hand.put("green", 2);
		hand.put("loco", 3);
		hand.put("red", 1);
		int uniqueColors = 0;
		int xWeight = 0;
		String rightmostColor = null;
		for (String c : Helper.colors) {
			if (hand.get(c) != 0) {
				uniqueColors++;
				rightmostColor = c;
				xWeight += 100 + 15 * hand.get(c);
			}
		}
		xWeight -= 100 + 15 * hand.get(rightmostColor);
		
		if (uniqueColors == 1) {
			for (String c : Helper.colors) {
				for (int i = 0; i < hand.get(c); i++) {
					g.drawImage(Helper.rotate(cards.get(c), 90), 20 + 15 * i, 860, 115, 180, null);
				}
			}
		} else if (uniqueColors > 1) {
			double gap = (615 - 15 * hand.get(rightmostColor) - xWeight) / (double) (uniqueColors - 1);
			double xOffset = 0;
			for (String c : Helper.colors) {
				if (hand.get(c) != 0) {
					for (int i = 0; i < hand.get(c); i++) {
						g.drawImage(Helper.rotate(cards.get(c), 90), 20 + (int) xOffset, 860, 115, 180, null);
						xOffset += 15;
					}
					xOffset += gap + 100;
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
	}
	
	public void loadImages() {
		cards = new HashMap<>();
		trains = new HashMap<>();
		tickets = new BufferedImage[1];
		try {
			map = ImageIO.read(Panel.class.getResource("/Images/europeMap.png"));

			cards.put("back", ImageIO.read(Panel.class.getResource("/Images/backCard.png")));
			for (String c : Helper.colors) {
				cards.put(c, ImageIO.read(Panel.class.getResource("/Images/Cards/" + c + "Card.png")));
			}

			trains.put("red", ImageIO.read(Panel.class.getResource("/Images/Trains/redTrain.png")));
			trains.put("blue", ImageIO.read(Panel.class.getResource("/Images/Trains/blueTrain.png")));
			trains.put("green", ImageIO.read(Panel.class.getResource("/Images/Trains/greenTrain.png")));
			trains.put("yellow", ImageIO.read(Panel.class.getResource("/Images/Trains/yellowTrain.png")));

			tickets[0] = ImageIO.read(Panel.class.getResource("/Images/backTicket.png"));
		} catch (Exception E) {
			System.out.println("FIRE IN THE Game!");
			return;
		}
	}
}
