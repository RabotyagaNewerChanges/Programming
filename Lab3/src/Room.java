public class Room extends Place {
    Room(String name, Essence[] content){
        super(name);
        this.setContent(content);
    }

    @Override
    public void setContent(Essence[] content) {
        this.content = content;
    }
}
