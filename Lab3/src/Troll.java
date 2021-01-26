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
