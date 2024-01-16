import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class MineRR {
    private final Queue<Wheelbarrow> waitingWheelbarrows;
    private final int numberOfRobots;
    int timeQuantum;
    private List<Wheelbarrow> wheelbarrows;

    public MineRR(int numberOfRobots, int timeQuantum) {
        this.numberOfRobots = numberOfRobots;
        this.timeQuantum = timeQuantum;
        this.waitingWheelbarrows = new LinkedList<>();
        this.wheelbarrows = new LinkedList<>();
    }

    public void start(List<String> lines) {
        int index = 0;
        int moment = Integer.parseInt(lines.get(0).split(" ")[0]);
        do {
            System.out.println("Moment " + moment);
            if (index < lines.size()) {
                update(readLine(lines.get(index)));
            } else {
                update();
            }
            index++;
            moment++;
        }
        while (index <= lines.size() || !wheelbarrows.isEmpty());
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
            try {
                Wheelbarrow barrow = new Wheelbarrow(
                        Integer.parseInt(parts[i * 4 + 1]),
                        parts[i * 4 + 2],
                        Integer.parseInt(parts[i * 4 + 3]),
                        Integer.parseInt(parts[i * 4 + 4]));
                wheelbarrows.add(barrow);
            } catch (Exception ignored) {
            }
        }
        return wheelbarrows;
    }

    public void updateQueue() {
        wheelbarrows = wheelbarrows.stream()
                .filter(t -> t.getUnloadingTime() != t.getExpectedTime())
                .collect(Collectors.toCollection(LinkedList::new));
        wheelbarrows = wheelbarrows.stream().filter(
                        t -> {
                            boolean bool = t.getTimeQuantum() < timeQuantum;
                            if (!bool) {
                                t.setTimeQuantum(0);
                                waitingWheelbarrows.add(t);
                            }
                            return bool;
                        })
                .collect(Collectors.toCollection(LinkedList::new));

        while (!waitingWheelbarrows.isEmpty() && wheelbarrows.size() < numberOfRobots) {
            wheelbarrows.add(waitingWheelbarrows.remove());
        }
    }

    public void work() {
        wheelbarrows.forEach(
                t -> {
                    System.out.print("      [" + t.getRockName() + " " + (t.getExpectedTime() - t.getUnloadingTime()) + "]");
                    t.updateTimeQuantum();
                    t.update();
                }
        );
        int numberOfFreeRobots = numberOfRobots - this.wheelbarrows.size();
        for (int i = numberOfFreeRobots; i > 0; i--) {
            System.out.print("[     ]");
        }
        System.out.println();

    }
}
