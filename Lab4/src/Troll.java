public class Troll extends Essence implements Transformable{

    private Thing[] clothes;
    private State state = State.Normal;
    private boolean hatIsOnForLongTime;

    Troll(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Троль по имени " + this.getName() + "\n";
    }

    public void raiseSomething (Thing thing) {
        System.out.println(this.getName() + " поднял: " + thing.getName());
    }

    public void lookAtSomething(Thing thing) {
        System.out.println(this.getName() + " начал рассматривать: " + thing.getName());
    }

    public void keepSomething(Thing thing) {
        System.out.println(this.getName() + " забрал с собой: " + thing.getName());
    }

    public void touch(Essence essence) {
        System.out.println(this.getName() + "дотронулся до: " + essence.getName());
    }
    public void push(Essence essence) {
        System.out.println(this.getName() + "толкнул: " + essence.getName());
    }

    public void cry(String sentence) {
        System.out.println(this.getName() + "крикнул: " + sentence);
    }

    public void go(Place place) {
        System.out.println(this.getName() + " вышел на " + place.getName());
    }

    public void drunkCoffee() {
        System.out.println(this.getName() + ": уже попили кофе");
    }

    public void goAway() {
        System.out.println(this.getName() + ": разошлись кто куда");
    }

    public void tryOn (Thing thing) {
        System.out.println(this.getName() + " примерил: " + thing.getName());
    }

    public void takeOff (Thing thing) {
        System.out.println(this.getName() + " снял: " + thing.getName());
    }

    public void lookAtMirror () {
        System.out.println(this.getName() + " оглядел себя со всех сторон");
    }

    public void gotHeadache() {
        System.out.println(this.getName() + " заработал головную боль");
    }

    public void cureHeadache() {
        System.out.println( "У " + this.getName() + " головная боль прошла после обеда");
    }

    public void openTheDoor() {
        class Door {
            private boolean isOpened = false;
            public void open() {
                this.isOpened = true;
                System.out.print("Теперь дверь открыта");
            }
        }
        System.out.println("Дверь открыл: " + this.getName());
        new Door().open();

    }

    public void freeze() {
        System.out.println(this.getName() + ": застыл от удивления");
    }

    @Override
    public void transform() {
        if(this.hatIsOnForLongTime){
            System.out.println(this.getName() + " начал превращаться!");
            this.state = State.Transformation;
        }
        else {
            System.out.println("Фух... " + this.getName()
                    + " - счастливчик. Еще бы чуть-чуть и последствия были бы необратимы.");
        }
    }

    public void setClothes(Thing[] clothes) {
        this.clothes = clothes;
    }

    public boolean isHatIsOnForLongTime() {
        return hatIsOnForLongTime;
    }

    public void setHatIsOnForLongTime(boolean hatIsOnForLongTime) {
        this.hatIsOnForLongTime = hatIsOnForLongTime;
    }
}
