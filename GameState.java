
import java.util.*;
import java.io.*;
import java.util.Map.Entry;
public class GameState{

    private Player[] playerArr;
    private int cardCounter, playerTurn;
    private HashMap<String, Integer> deck;
    private String[] cards;
    //private ArrayList<Ticket> ticketPile;
    private ArrayList<City> cities;
    private HashMap<City, ArrayList<Route>> board;

    public GameState(){
        try{
            ArrayList<City> cities = readCities();
            createMap(cities);
            System.out.println("success");
        }
        catch(Exception e){
        System.out.println("error");
        }
        createDeck();
        printDeck();
        cards = new String[5];
        for(int i = 0; i < 5; i++){
            cards[i] = drawCard();
        }
        System.out.println(Arrays.toString(cards));
        cardCounter = 0;
        playerTurn = 0;
        playerArr = new Player[4];
        playerArr[0] = new Player("YELLOW");
        playerArr[1] = new Player("BLUE");
        playerArr[2] = new Player("GREEN");
        playerArr[3] = new Player("RED");
        for(Player p : playerArr){
            System.out.println(p);
        }
        tryDrawCard("deck");
        tryDrawCard("deck");
        tryDrawCard("deck");
        tryDrawCard("deck");

        for(Player p : playerArr){
            System.out.println(p);
        }
    }

    public void printDeck(){
        for(Entry<String, Integer> e : deck.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

//setting up game
    public void createDeck(){
        deck = new HashMap<String, Integer>();
        for (String s : Helper.colorSet) {
            if (s.equals("loco")) {
                deck.put(s, 14);
            } else {
                deck.put(s, 12);
            }
        }
    }
    
    public String drawCard(){
        int count = 0;
        for (String s : Helper.colorSet) {
            count += deck.get(s);
        }
        int drawn = (int) (count * Math.random());
        for (String s : Helper.colorSet) {
            drawn -= deck.get(s);
            if (drawn < 0) {
                deck.put(s, deck.get(s) - 1);
                return s;
            }
        }
        return "no";        
    }

    public void drawStartingTickets(){

    }
    public static void main(String[] args) {
        System.out.println("hello");
        GameState g = new GameState();
    }

    public void createMap(ArrayList<City> cities) throws IOException{
        board = new HashMap<>();
        Scanner sc = new Scanner(new File("routes.txt"));
        ArrayList<Route> routes = new ArrayList<Route>();
        City city1, city2;
        while(sc.hasNextLine()){
          String[] info = sc.nextLine().split(" ");
          System.out.println(Arrays.toString(info));
          city1 = searchCity(info[0], cities);
          System.out.println(city1);
          city2 = searchCity(info[1], cities);
          routes.add(new Route(city1, city2, Integer.parseInt(info[2]), info[3], Boolean.parseBoolean(info[4]), Integer.parseInt(info[5])));
        }
        System.out.println("done");
        for(Route r : routes){
            //System.out.println("-");
            System.out.println(r);
        }
        
        for(City k : cities){
            ArrayList<Route> tempRouteList = new ArrayList<>();
            for(Route r : routes){
                if(r.getCity1().equals(k) || r.getCity2().equals(k)){
                    tempRouteList.add(r);
                }
            }
            board.put(k, tempRouteList);
        } 

        for(Entry<City, ArrayList<Route>> tentry : board.entrySet()){
            System.out.println("City: " + tentry.getKey() + " Routes " + tentry.getValue());
        }
        
    }

    public City searchCity(String city, ArrayList<City> cityList){
        for(City k : cityList){
            if(k.name().equals(city)){
                return k;
            }
        }
        return null;
    }

    public ArrayList<City> readCities() throws IOException{
        System.out.println("read cities");
        Scanner sc = new Scanner(new File("cities.txt"));
        ArrayList<City> cities = new ArrayList<>();
        while(sc.hasNextLine()){
            String[] info = sc.nextLine().split(" ");
            cities.add(new City(info[0]));
        }
        System.out.println(cities);
        return cities;
    }
//end of game setup
    public boolean tryDrawCard(String choice){
        //choice will either be deck or faceup index
        if(cardCounter > 2){
            endTurn();
            return false;
        }
        if(choice.equals("deck")){
            String c = drawCard();
            playerArr[playerTurn].addCard(c);
            cardCounter++;
            return true;
        }
        int index = Integer.parseInt(choice);
        String c = getCardsIndex(index);
        if(c.equals("loco")){
            if(cardCounter == 0){
            playerArr[playerTurn].addCard(c);
            endTurn();
            return true;
            }
            return false;
        }
        playerArr[playerTurn].addCard(c);
        return true;

        
    }

    public void endTurn(){
        playerTurn++;
        cardCounter = 0;

    }

    public String getCardsIndex(int i){
        String k = cards[i];
        cards[i] = drawCard();
        int lococounter = 0;
        for(String c : cards){
            if (c.equals("loco"))
            lococounter++;
        }
        if (lococounter >= 3){
            for(int j = 0; j < 5; j++){
                cards[j] = drawCard();
            }
        }
        return k;
    }
    public String[] getCards(){
        return cards;
    }

    

    // public void createRoutes

}