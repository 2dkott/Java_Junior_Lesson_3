import java.io.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        Deparment deparment = new Deparment("Отдел Кадров");
        Employee employee = new Employee("Петр Петров", deparment);
        System.out.println("Before Serialization-----");
        System.out.println(deparment);
        System.out.println(employee);

        System.out.println("After Serialization-----");
        String deptFileName = saveObject2File(deparment);
        Deparment uploadedDepartment = getObject(deptFileName);

        String employeeFileName = saveObject2File(employee);
        Employee uploadedEmployee = getObject(employeeFileName);

        System.out.println(uploadedDepartment);
        System.out.println(uploadedEmployee);
    }

    static String saveObject2File(Serializable object) throws IOException {
        String fileName = object.getClass().getName() + "_" + UUID.randomUUID().toString();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
        return fileName;
    }

    static <T extends SerializableWithGenId> T getObject(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)){
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            T object = (T) objectInputStream.readObject();
            objectInputStream.close();
            object.generateId();
            return object;
        } catch (FileNotFoundException e) {
            System.out.printf("Файл '%s' не найден!", fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}