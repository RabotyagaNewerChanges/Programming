public class MumiDol extends Place implements Transformable{

    MumiDol(String name) {
        super(name);
    }

    @Override
    public void setContent(Essence[] content) {
        this.content = content;
    }

    @Override
    public void transform() {
        System.out.println(this.getName() + " превращается в арену волшебства и удивительных событий");
    }
}
