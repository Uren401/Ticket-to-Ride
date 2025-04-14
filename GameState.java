
import java.util.*;
import java.io.*;
import java.util.Map.Entry;
public class GameState{

    private Player[] playerArr;
    private int cardCounter, playerTurn, endgame;
    private HashMap<String, Integer> deck;
    private HashMap<String, Integer> discard;
    private String[] cards;
    private ArrayList<Ticket> ticketPile, longRoutes;
    private ArrayList<City> cities;
    private HashMap<City, ArrayList<Route>> board;


    public GameState(){
        try{
            cities = readCities();
            createMap(cities);
            createTickets();
            System.out.println("success");
        }
        catch(Exception e){
        System.out.println("error");
        }
        createDecks();
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
        endgame = 0;
        drawStartingTickets();
        printPlayers();

     
       
    }

    public void printPlayers(){
        for(Player p : playerArr){
            System.out.println(p);
        }
    }

    

    public static void main(String[] args) {
        System.out.println("hello");
        GameState g = new GameState();
        g.test();
    }

    public void test(){
        Scanner sc = new Scanner(System.in);
        /*for(int i = 0; i < 10; i++){
            System.out.println("Player " + (playerTurn + 1));
            System.out.println("draw card");
            String choice = sc.nextLine();
            System.out.println("//////////////////////////");
            tryDrawCard(choice);
            for(Player p : playerArr){
                System.out.println(p);
            }
            System.out.println(Arrays.toString(cards));

        }  */
        //   System.out.println("buy route");
        //   String[] cardarr = new String[]{"orange", "orange", "orange"};
        //   ArrayList<String> cards = new ArrayList<>(Arrays.asList(cardarr)); 
        //   //System.out.println(cards);
        //   System.out.println(cities);
        //   System.out.println(buyRoute("Munchen", "Wien", cards));
        //   for(Player p : playerArr){
        //     System.out.println(p);
        //}
            String x = sc.nextLine();
            while(!x.equals("stop")){
                if(x.equals("buy route")){
                    System.out.println("cards");
                    String[] cardarr = sc.nextLine().split(" ");
                    ArrayList<String> cards = new ArrayList<>(Arrays.asList(cardarr)); 
                    System.out.println("city 1");
                    String c1 = sc.nextLine();
                    System.out.println("city 2");
                    String c2 = sc.nextLine();
                    System.out.println(buyRoute(c1, c2, cards));
                }
                if(x.equals("check tickets")){
                    for(Ticket t : playerArr[playerTurn].getTickets()){
                        System.out.println(checkCompleted(t, playerArr[playerTurn].getColor()));
                    }
                }
                x = sc.nextLine();
            }
    }

    public void createMap(ArrayList<City> cities) throws IOException {
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

    public void createTickets() throws IOException{
        ticketPile = new ArrayList<>();
        longRoutes = new ArrayList<>();
        Scanner sc = new Scanner(new File("tickets.txt"));
        while(sc.hasNextLine()){
            String[] info = sc.nextLine().split(" ");
            City city1 = searchCity(info[0], cities);
            City city2 = searchCity(info[1], cities);
            Ticket t = new Ticket(city1, city2, Integer.parseInt(info[2]));
            if(t.getPoints() >= 20){
                longRoutes.add(t);
            }else{
                ticketPile.add(t);
            }
        }
        //Collections.shuffle(ticketPile);
        Collections.shuffle(longRoutes);
    }

    public Ticket drawTicket(){
        return ticketPile.remove(0);
    }

    public Ticket getLongTicket(){
        return longRoutes.remove(0);
    }

    //end of game setup

    public boolean checkCompleted(Ticket t, String p){
        System.out.println(t);
        City city1 = t.city1();
        City city2 = t.city2();
        ArrayList<City> visited = new ArrayList<>();
        return checkCompleted(city1, city2, p, visited) || checkCompleted(city2, city1, p, visited);
    }

    public boolean checkCompleted(City c1, City c2, String p, ArrayList<City> visited){
        ArrayList<Route> routeliest = board.get(c1);
        visited.add(c1);
        for(Route r : routeliest){
            if(r.boughtColor() != null && r.boughtColor().equals(p)){
                 if(r.getCity2().equals(c2) && !visited.contains(c2)){
                    return true;
                 }
                 return checkCompleted(r.getCity2(), c2, p, visited);
            }
            return false;
        }
        return false;
    }
    
    public void drawStartingTickets(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Ticket> temp = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            temp = new ArrayList<>();
            temp.add(getLongTicket());
            for(int j = 0; j < 3; j++){
                temp.add(drawTicket());
            }

            System.out.println("Drawn tickets " + temp);
            System.out.println("Select the indexes of up to 2 tickets to discard (type STOP to end)");
            String x = sc.nextLine();
            while(!x.equals("STOP") && temp.size() >= 4){
                int index = Integer.parseInt(x);
                temp.remove(index);
                System.out.println(temp);
                x = sc.nextLine();
            }
            for(Ticket t : temp){
                playerArr[i].addTicket(t);
            }
        }

    }
   
    public boolean tryDrawCard(String choice){
        //choice will either be deck or faceup index
        if(cardCounter >= 2){
            return false;
        }
        if(choice.equals("deck")){
            String c = drawCard();
            playerArr[playerTurn].addCard(c);
            cardCounter++;
            if(cardCounter == 2){
                endTurn();
            }
            return true;
        }
        int index = Integer.parseInt(choice);
        String c = drawCard(index);
        if(c.equals("loco")){
            if(cardCounter == 0){
            playerArr[playerTurn].addCard(c);
            endTurn();
            return true;
            }
            return false;
        }
        playerArr[playerTurn].addCard(c);
        cardCounter++;
        if(cardCounter == 2){
            endTurn();
        }
        return true;
    }

    public void endTurn(){
        if(playerArr[playerTurn].numTrains() <= 2 || endgame != 0){
            endgame++;
        }
        if(endgame == 4){
            endGame();
        }
        playerTurn = (playerTurn+1)%4;
        cardCounter = 0;

    }

    public void printDeck(){
        for(Entry<String, Integer> e : deck.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

//setting up game
    public void createDecks(){
        deck = new HashMap<String, Integer>();
        discard = Helper.getEmptyDeck();
        for (String s : Helper.colors) {
            if (s.equals("loco")) {
                deck.put(s, 14);
            } else {
                deck.put(s, 12);
            }
        }
    }
    
    private String drawCard(){
        int count = 0;
        for (String s : Helper.colors) {
            count += deck.get(s);
        }
        int drawn = (int) (count * Math.random());
        for (String s : Helper.colors) {
            drawn -= deck.get(s);
            if (drawn < 0) {
                deck.put(s, deck.get(s) - 1);
                return s;
            }
        }
        return "no";        
    }

    public String drawCard(int i){
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
//buy routes
    public boolean buyRoute(String c1, String c2, ArrayList<String> cards){
        if(cards.isEmpty()){
            return false;
        }
        City city1 = searchCity(c1, cities);
        City city2 = searchCity(c2, cities);
        ArrayList<Route> routeslist = board.get(city1);
        System.out.println(routeslist);
        String cardcolor = "";
        for(String s:cards){
            System.out.println(s);
            if(!s.equals("loco")){
                cardcolor = s;
        }
    }
        if(cardcolor.equals(""))
        cardcolor = "loco";
        System.out.println("color " + cardcolor);
        Route purchaseRoute = null;
        for(Route r : routeslist){
            if(((r.getCity1().equals(city1) && r.getCity2().equals(city2))|| (r.getCity2().equals(city1) && r.getCity1().equals(city2))) && (r.color().equals("gray") || r.color().equals(cardcolor))){
                purchaseRoute = r;
            }
        }
        if(purchaseRoute == null){
            System.out.println("no route found");
            return false;
        }
        System.out.println(purchaseRoute);
         
        int locomotives = purchaseRoute.getLocomotives();
        int locomotivecards = 0;
        for(String x : cards){
            if(x.equals("loco"))
            locomotivecards++;
        }
        if(cards.size() < purchaseRoute.getLength()){
            return false;
        }
        if(!purchaseRoute.isTunnel() && locomotives == 0){
            purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
            playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
            //endTurn();
            return true;
        }
        if(locomotives != 0){
            if(locomotivecards >= locomotives){
                purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
                playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
                //endTurn();
                return true;
            }
            return false;
        }
        return buyTunnel(purchaseRoute, cardcolor);


    }

    public boolean buyTunnel(Route purchaseRoute, String color){
        ArrayList<String> extraCards = new ArrayList<>();
        int extra = 0;
        for(int i = 0; i < 3; i++){
            String c = drawCard();
            extraCards.add(c);
            if(c.equals(color)){
                extra++;
            }
        }
        if(extra == 0){
            purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
            endTurn();
            return true;
        }
        //ask for extra cards
        return false;


    }

    public boolean placeStation(String s){
        City c = searchCity(s, cities);
        if(c.addStation(playerArr[playerTurn].getColor())){
            int price = 4 - playerArr[playerTurn].getStations();
            ArrayList<String> cards = new ArrayList<String>();
            if(cards.size() >= price){
                playerArr[playerTurn].useStation();
                endTurn();
                return true;
            }
        }
        return false;
    }

    public void drawTickets(){
        ArrayList<Ticket> tlist = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            tlist.add(drawTicket());
        }


        endTurn();
    }
//end game
    public void endGame(){
        int s = 0;
        for(Player p : playerArr){
            s += checkStations(p);
            s += checkCompleted(p);
            p.addPoints(s);
        }
        //  check european express
        // switch to end screen
    }

    public int checkStations(Player p){
        return 4 * p.getStations();
    }

    public int checkCompleted(Player p){
        return 0;
    }

}
