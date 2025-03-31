import java.util.*;
public class GameState{

    private Player[] playerArr;
    private int cardCounter, playerTurn;
    private HashMap<String, int> deck;
    private String[] cards;
    private ArrayList<Ticket> ticketPile;
    private ArrayList<City> cities;

    public GameState(){
        createMap();
    }

    public void createMap(){
        board = new HashMap<>();
        board.put("Lisbon")
    }

}