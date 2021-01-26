public class Thing extends Essence implements Layable, Transformable{

    private Essence place;
    private State state = State.Normal;
    private boolean LayingForLongTime;

    Thing(String name) {
        super(name);
        this.setLayingForLongTime(false);
    }

    @Override
    public void setPlace(Essence place) {
        this.place = place;
    }

    @Override
    public void transform() {
        if(LayingForLongTime) {
            System.out.println( "прошло много времени... "+ this.getName()
                    + " долго лежит в шляпе и начинает трансформироваться!");
            this.state = State.Transformation;
        }
        else {
            System.out.println("ничего не происходит...");
        }
    }

    public boolean getLayingForLongTime() {
        return LayingForLongTime;
    }

    public void setLayingForLongTime(boolean LayingForLongTime) {
        this.LayingForLongTime = LayingForLongTime;
    }

    @Override
    public String toString() {
        return this.getName() + " находится в: " + this.place.getName() + "\n";
    }
}
