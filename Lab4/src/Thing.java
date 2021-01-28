public class Thing extends Essence implements Layable, Transformable{

    private Essence place;
    private State state = State.Normal;
    private boolean LayingForLongTime;

    Thing(String name) {
        super(name);
        this.setLayingForLongTime(false);
    }

    @Override
    public void setPlace(Essence place) throws PlaceIsNotValid {
        if(place != null) {
            this.place = place;
            System.out.print(this.getName() + " теперьб находится на/у: " + place.getName());
        }
        else {
            throw new PlaceIsNotValid();
        }
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
