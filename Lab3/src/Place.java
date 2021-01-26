abstract class Place extends Essence{

    protected Essence[] content;
    Place(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if(content != null) {
            String str = new String();
            for(Essence item : content) {
                str += item.getName() + ", ";
            }
            return this.getName() + " сейчас содержит: " + str;
        }
        else {
            return this.getName() + ": пусто";
        }
    }

    public abstract void setContent(Essence[] content);
}
