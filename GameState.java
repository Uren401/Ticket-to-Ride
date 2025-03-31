import java.util.*;
public class GameState{

    private Player[] playerArr;
    private int cardCounter, playerTurn;
    private HashMap<String, int> deck;
    private String[] cards;
    private ArrayList<Ticket> ticketPile;
    private ArrayList<City> cities;
    private HashMap<City, ArrayList<Route>> board;
    private Scanner sc;

    public GameState(){
        //createMap();
        
        ArrayList<City> cities = readCities();
    }

    public void createMap(ArrayList<City> cities){
        board = new HashMap<>();
        Scanner sc = new Scanner("routes.txt");
        ArrayList<Route> routes = new ArrayList<Route>();
        City city1, city2;
        while(sc.hasNextLine()){
          String[] info = sc.nextLine().split(" ");
          city1 = searchCity(info[0], cities);
          city2 = searchCity(info[0], cities);
          routes.add(new Route(city1, city2, ));
        }
        
    }

    public City searchCity(String city, ArrayList<City> cityList){
        for(City k : cityList){
            if(k.name().equals(city)){
                return k;
            }
        }
    }

    public ArrayList<City> readCities(){
        Scanner sc = new Scanner("cities.txt");
        ArrayList<City> cities = new ArrayList<>();
        while(sc.hasNextLine()){
            cities.add(new City(sc.nextLine()));
        }
        return cities;
    }

   // public void createRoutes

}