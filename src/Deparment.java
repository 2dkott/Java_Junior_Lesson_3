import java.io.Serializable;
import java.util.Random;

public class Deparment implements Serializable, SerializableWithGenId {

    private transient int id;
    
    private final String name;

    public Deparment(String name) {
        this.name = name;
        this.id = genId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int genId() {
        return new Random().nextInt(1000,2000);
    }
    @Override
    public void generateId() {
        if (id==0) {
            id = genId();
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nId: %s\n", name, id);
    }
}
