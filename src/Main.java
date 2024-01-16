import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int numberOfRobots=Integer.parseInt(args[0]);
        int timeQuantum=Integer.parseInt(args[1]);
        String filePath = args[2];
        int strategy=Integer.parseInt(args[3]);

        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            switch (strategy) {
                case 1 -> {
                    MineFCFS mineFCFS = new MineFCFS(numberOfRobots);
                    mineFCFS.start(lines);
                }
                case 2 -> {
                    MineRR mineRR = new MineRR(numberOfRobots, timeQuantum);
                    mineRR.start(lines);
                }
                case 3 -> {
                    MineSJV mineSJV = new MineSJV(numberOfRobots);
                    mineSJV.start(lines);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}