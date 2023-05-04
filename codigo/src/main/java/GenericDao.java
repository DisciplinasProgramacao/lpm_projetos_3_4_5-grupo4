import java.io.*;
import java.util.List;

public class GenericDao<T> {


    public void save(List<T> list, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(list);
        }
    }

    public List<T> load(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) in.readObject();
        }
    }
}
