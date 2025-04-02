
import java.util.*;
import java.io.*;
public class GameState{

    //private Player[] playerArr;
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
                if(r.getCity1().equals(k)){
                    tempRouteList.add(r);
                }
            }
            board.put(k, tempRouteList);
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

    

    // public void createRoutes

}