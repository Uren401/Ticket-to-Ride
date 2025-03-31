
public class Route {

    private int length, points;
    private String color, boughtColor;
    private boolean isTunnel;

    public Route(int l, String c, boolean t) {
        //des = d;
        length = l;
        color = c;
        isTunnel = t;
        if(length == 1){
            points = 1;
        }
        else if(length == 2){
            points = 2;
        }else if(length == 3){
            points = 4;
        }else if(length == 4){
            points = 7;
        }else if(length == 6){
            points = 15;
        }else if(length == 8){
            points = 21;
        }
    }

    // public City getDestination(){
    //     return des;
    // }

    public int getLength(){
        return length;
    }
    
    public int getPoints(){
        return points;
    }

    public String color(){
        return color;
    }

}
