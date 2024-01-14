import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Mine {
    protected final Queue<Wheelbarrow> waitingWheelbarrows;
    protected int numberOfRobots;
    protected List<Wheelbarrow> wheelbarrows;

    public Mine(int numberOfRobots) {
        this.numberOfRobots = numberOfRobots;
        this.wheelbarrows = new LinkedList<>();
        this.waitingWheelbarrows = new LinkedList<>();
    }

    public void start(List<String> lines) {
        int index = 0;
        do {
            System.out.println("Moment " + index);
            if (index < lines.size()) {
                update(readLine(lines.get(index)));
            } else {
                update();
            }
            index++;
        }
        while (!wheelbarrows.isEmpty());
    }

    private void update(List<Wheelbarrow> wheelbarrows) {
        receivedWheelbarrow(wheelbarrows);
        work();
        updateQueue();
    }

    private void update() {
        work();
        updateQueue();
    }

    protected void work() {
        wheelbarrows.forEach(
                t -> {
                    System.out.print("      [" + t.getRockName() + " " + t.getQuantity() + "]");
                    t.update();
                }
        );
        int numberOfFreeRobots = numberOfRobots - this.wheelbarrows.size();
        for (int i = numberOfFreeRobots; i > 0; i--) {
            System.out.print("[     ]");
        }
        System.out.println();

    }

    protected void updateQueue() {
        wheelbarrows = wheelbarrows.stream()
                .filter(t -> t.getUnloadingTime() < t.getExpectedTime())
                .collect(Collectors.toCollection(LinkedList::new));

        while (!waitingWheelbarrows.isEmpty() && wheelbarrows.size() < numberOfRobots) {
            wheelbarrows.add(waitingWheelbarrows.remove());
        }
    }

    private void receivedWheelbarrow(List<Wheelbarrow> list) {
        list.forEach(
                (t) -> {
                    System.out.println("      wheelbarrow arrived " + t);
                    if (this.wheelbarrows.size() < this.numberOfRobots)
                        wheelbarrows.add(t);
                    else
                        waitingWheelbarrows.add(t);
                }
        );
    }

    private List<Wheelbarrow> readLine(String line) {
        String[] parts = line.split(" ");
        int objectQuantity = parts.length / 4;
        List<Wheelbarrow> wheelbarrows = new ArrayList<>();
        for (int i = 0; i < objectQuantity; i++) {
            wheelbarrows.add(
                    new Wheelbarrow(
                            Integer.parseInt(parts[i * 4 + 1]),
                            parts[i * 4 + 2],
                            Integer.parseInt(parts[i * 4 + 3]),
                            Integer.parseInt(parts[i * 4 + 4])
                    )
            );
        }
        return wheelbarrows;
    }
}
