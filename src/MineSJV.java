import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MineSJV {
    private final int numberOfRobots;
    private List<Wheelbarrow> waitingWheelbarrows;
    private List<Wheelbarrow> wheelbarrows;

    public MineSJV(int numberOfRobots) {
        this.numberOfRobots = numberOfRobots;
        this.wheelbarrows = new LinkedList<>();
        this.waitingWheelbarrows = new LinkedList<>();
    }

    public void start(List<String> lines) {
        int moment = Integer.parseInt(lines.get(0).split(" ")[0]);
        int index = 0;
        do {
            if (index < lines.size()) {
                    update(readLine(lines.get(index)),moment);
            } else {
                update(moment);
            }
            index++;
            moment++;
        }
        while (index <= lines.size() || !wheelbarrows.isEmpty());
    }

    private void update(List<Wheelbarrow> wheelbarrows,int moment) {
        receivedWheelbarrow(wheelbarrows);
        System.out.println("Moment"+moment);
        work();
    }

    private void update(int moment) {
        updateQueue();
        if(!this.wheelbarrows.isEmpty()) {
            System.out.println("Moment"+moment);
            work();
        }
        //updateQueue();
    }

    protected void work() {
        wheelbarrows.forEach(
                t -> {
                    System.out.print("      [" + t.getRockName() + " " + (t.getExpectedTime() - t.getUnloadingTime()) + "]");
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
        waitingWheelbarrows = waitingWheelbarrows.stream()
                .sorted(Comparator.comparingInt(Wheelbarrow::getExpectedTime))
                .collect(Collectors.toCollection(LinkedList::new));
        wheelbarrows = wheelbarrows.stream()
                .filter(t -> t.getUnloadingTime() < t.getExpectedTime())
                .collect(Collectors.toCollection(LinkedList::new));

        while (!waitingWheelbarrows.isEmpty() && wheelbarrows.size() < numberOfRobots) {
            wheelbarrows.add(waitingWheelbarrows.remove(0));
        }
    }

    private void receivedWheelbarrow(List<Wheelbarrow> list) {
        list.forEach(
                (t) -> {
                    System.out.println("      wheelbarrow arrived " + t);
                });

        waitingWheelbarrows.addAll(list);
        waitingWheelbarrows = waitingWheelbarrows.stream()
                .sorted(Comparator.comparingInt(Wheelbarrow::getExpectedTime))
                .collect(Collectors.toCollection(LinkedList::new));
        updateQueue();

    }

    private List<Wheelbarrow> readLine(String line) {
        String[] parts = line.split(" ");
        int objectQuantity = parts.length / 4;
        List<Wheelbarrow> wheelbarrows = new LinkedList<>();
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
}
