import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\jakub\\OneDrive\\Pulpit\\java\\szeregowanie\\src\\dane.txt";
        Mine mine = new MineRR(2, 1);
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            mine.start(lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}