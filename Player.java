
import java.util.*;

public class Player{

    private int score, numTrains, numStations;
    private HashMap<String, Integer> cards;
    private ArrayList<Ticket> tickets;
    private String color;

    public Player(String c){

        score = 0;
        numTrains = 45;
        numStations = 3;

        cards = Helper.getColorMap();
        color = c;
    }

    public void drawTicket(Ticket t){
        tickets.add(t);
    }

    public void addCard(String c){
        cards.put(c, cards.get(c) + 1);
    }

    public void removeCard(String c){
        if(cards.get(c) > 0){
            cards.put(c, cards.get(c) - 1);
        }
        else{
            System.out.println("You don't have any " + c + " cards left.");
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Player Color: " + color + "\n");
        sb.append("Score: " + score + "\n");
        sb.append("Number of Trains: " + numTrains + "\n");
        sb.append("Number of Stations: " + numStations + "\n");
        sb.append("Cards: \n");

        for(Map.Entry<String, Integer> entry : cards.entrySet()){
            sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }

        return sb.toString();
    }

}