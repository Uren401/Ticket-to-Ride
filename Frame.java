
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame {
	private static final int WIDTH = 1550, HEIGHT = 1140;
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
		try {
			paintButtons();
		} catch (Exception e) {
			System.out.println("FIRE IN THE Frame!" + e.getMessage());
		}
		revalidate();
		repaint();
	}

	public void toEnd() {
		remove(panels[1]);
		add(panels[2]);
		revalidate();
		repaint();
	}

	public void paintButtons() throws IOException {
		Scanner sc;
		try {
			sc = new Scanner(new File("cities.txt"));
		} catch (Exception e) {
			System.out.println("FIRE IN THE Frame!");
			return;
		}
		
		while (sc.hasNext()) {
			String[] split = sc.nextLine().split(" ");
			JButton city = new JButton();
			city.setBounds(Integer.parseInt(split[1]) - 10, Integer.parseInt(split[2]) - 10, 20, 20);
			panels[1].add(city);
		}
	}
}
