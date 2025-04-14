public class Ticket{

    private City city1, city2;
    private int points;
    private boolean completed;

    public Ticket(City city1, City city2, int p){

        this.city1 = city1;
        this.city2 = city2;
        points = p;
        completed = false;
    }


    public int getPoints(){
        return points;
    }
    public City city1(){
        return city1;
    }
    public City city2(){
        return city2;
    }
    public String toString() {
        return "Ticket from " + city1 + " to " + city2 + " " + points + " " + completed;
    }
}
