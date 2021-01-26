public class Miracle implements Creatable{

    private boolean isCreated;

    Miracle(){
        setCreated(false);
    }

    @Override
    public void create() {
        System.out.print("Сотворилось чудо: ");
        setCreated(true);
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }
}
