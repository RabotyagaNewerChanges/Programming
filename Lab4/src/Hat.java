import java.net.PortUnreachableException;

public class Hat extends Thing{
    private Thing content;

    Hat() {
        super("Шляпа");
    }

    Hat(Essence place) throws PlaceIsNotValid {
        super("Шляпа");
        this.setPlace(place);
    }

    public Thing getContent() {
        return content;
    }

    public void setContent(Thing content) {
        this.content = content;
    }

    @Override
    public String toString() {
        if (this.content != null)
            return super.toString() + this.getName() + " сейчас содержит: " + this.content.getName();
        else
            return super.toString() + this.getName() + ": пусто";
    }
}
