public class Cloud extends Essence{

    Cloud(String name) {
        super(name);
    }

    public void fly() {
        System.out.println(this.getName() + " выплыло на веранду, мягко спустилось и повисло в воздухе над землей");
    }

    public void doSomersault () {
        System.out.println(this.getName() + " поднялось над землей и описала небольшую изящную дугу");
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
