package storable;

public class Location {
    private double x;
    private double y;
    private String name;

    public Location(double x, double y, String name) {
        setX(x);
        setY(y);
        setName(name);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setName(String name) {
        if(!name.equals(""))
            this.name = name;
        else
            this.name = null;
    }

    public String getName() {
        return name;
    }
}
