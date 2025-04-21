
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel extends Panel implements MouseListener {
	private BufferedImage map;
	private Map<String, BufferedImage> cards, trains, stations;
	private BufferedImage[] tickets;
	private ArrayList<Integer> citiesX, citiesY;
	private ArrayList<String> cities;
	// public static GameState game;
	
	public GamePanel(Frame f) {
		super(f);
		addMouseListener(this);
		// game = new GameState();
	}
	
	public void paint(Graphics g) {
		g.drawImage(map, 0, 0, 1300, 840, null);

		g.drawImage(tickets[0], 1330, 5, 180, 115, null);
		g.drawImage(cards.get("back"), 1330, 125, 180, 115, null);
		String[] deck = {"red", "black", "loco", "orange", "orange"};
		for (int i = 0; i < 5; i++) {
			g.drawImage(cards.get(deck[i]), 1330, 245 + 120 * i, 180, 115, null);
		}

		paintHand(g);

		g.setFont(new Font("Sans Serif", Font.BOLD, 50));
		g.drawImage(trains.get("green"), 1400, 900, 100, 50, null);
		g.drawImage(trains.get("red"), 1280, 900, 100, 50, null);
		g.drawImage(trains.get("blue"), 1160, 900, 100, 50, null);
		g.drawImage(trains.get("yellow"), 1040, 900, 100, 50, null);
		g.drawString("45", 1060, 940);
		g.drawString("45", 1180, 940);
		g.drawString("45", 1300, 940);
		g.drawString("45", 1420, 940);
		g.setColor(Color.GREEN);
		g.drawString("0", 1420, 1020);
		g.setColor(Color.RED);
		g.drawString("0", 1300, 1020);
		g.setColor(Color.BLUE);
		g.drawString("0", 1180, 1020);
		g.setColor(Color.YELLOW);
		g.drawString("0", 1060, 1020);

		for (int i = 2; i >= 0; i--) {
			g.drawImage(stations.get("blue"), 965, 880 + 40 * i, 55, 90, null);
		}

		g.drawImage(tickets[0], 750, 870, 200, 130, null);
		g.drawImage(tickets[0], 750, 900, 200, 130, null);
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

		if (x < 1300 && y < 840) {
			for (int i = 0; i < cities.size(); i++) {
				if (x > citiesX.get(i) - 25 && x < citiesX.get(i) + 25 && y > citiesY.get(i) - 25 && y < citiesY.get(i) + 25) {
					// game.tryCity(cities.get(i));
				}
			}
		} else if (y < 840 && x > 1330 && x < 1510) {
			if (y / 5 % 24 != 0) {
				switch (y / 120) {
					case 0:
						// game.tryTicketDeck();
						break;
					case 1:
						// game.tryCardDeck();
						break;
					default:
						// game.tryCard((y / 120 - 2));
				}
			}
		} else if (x > 965 && x < 1020 && y > 880 && y < 1050) {
			// game.tryStation();
		}
		getFrame().toEnd();
	}
	
	public void loadImages() {
		cards = new HashMap<>();
		trains = new HashMap<>();
		stations = new HashMap<>();
		tickets = new BufferedImage[1];
		cities = new ArrayList<>();
		citiesX = new ArrayList<>();
		citiesY = new ArrayList<>();
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

			stations.put("red", ImageIO.read(Panel.class.getResource("/Images/Stations/redStation.png")));
			stations.put("blue", ImageIO.read(Panel.class.getResource("/Images/Stations/blueStation.png")));
			stations.put("green", ImageIO.read(Panel.class.getResource("/Images/Stations/greenStation.png")));
			stations.put("yellow", ImageIO.read(Panel.class.getResource("/Images/Stations/yellowStation.png")));

			tickets[0] = ImageIO.read(Panel.class.getResource("/Images/backTicket.png"));

			Scanner sc = new Scanner(new File("cities.txt"));
			while (sc.hasNext()) {
				cities.add(sc.next());
				citiesX.add(Integer.parseInt(sc.next()));
				citiesY.add(Integer.parseInt(sc.next()));
			}
		} catch (Exception E) {
			System.out.println("FIRE IN THE Game!");
			return;
		}
	}
}
