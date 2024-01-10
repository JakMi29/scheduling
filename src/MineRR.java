import java.util.LinkedList;
import java.util.stream.Collectors;

public class MineRR extends Mine {
    int timeQuantum;

    public MineRR(int numberOfRobots, int timeQuantum) {
        super(numberOfRobots);
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void updateQueue() {
        wheelbarrows = wheelbarrows.stream()
                .filter(t -> t.getUnloadingTime() != t.getExpectedTime())
                .collect(Collectors.toCollection(LinkedList::new));
        wheelbarrows = wheelbarrows.stream().filter(
                        t -> {
                            boolean bool = t.getTimeQuantum() != timeQuantum;
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

    @Override
    public void work() {
        wheelbarrows.forEach(
                t -> {
                    System.out.print("      [" + t.getRockName() + " " + t.getQuantity() + "]");
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
