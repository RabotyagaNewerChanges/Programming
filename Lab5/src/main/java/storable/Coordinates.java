package storable;

public class Coordinates {
    private int x;
    private Double y;

    public Coordinates(int x, Double y) {
        setX(x);
        setY(y);
    }


    public void setX(int x){
            this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(Double y){
            this.y = y;

    }

    public Double getY() {
        return y;
    }
}
