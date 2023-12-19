import java.io.Serializable;
import java.util.Random;

public class Employee implements Serializable, SerializableWithGenId {

    private final String name;
    private final Deparment deparment;
    private transient int id;

    public Employee(String name, Deparment deparment) {
        this.name = name;
        this.deparment = deparment;
        this.id = new Random().nextInt(2000,3000);
    }

    public String getName() {
        return name;
    }

    public Deparment getDeparment() {
        return deparment;
    }

    public int getId() {
        return id;
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
        return String.format("Employee Name: %s\nId: %s\nDepartment: %s\n", name, id, deparment.getName());
    }
}
