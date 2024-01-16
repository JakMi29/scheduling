public class Wheelbarrow {
    private final int number;
    private final String rockName;
    private final int weight;
    private final int quantity;
    private final int expectedTime;
    private int unloadingTime;
    private int timeQuantum;

    public Wheelbarrow(int number, String rockName, int weight, int quantity) {
        this.number = number;
        this.rockName = rockName;
        this.weight = weight;
        this.quantity = quantity;
        this.unloadingTime = 0;
        this.expectedTime = quantity * weight;
    }
    public int getTimeQuantum() {
        return timeQuantum;
    }
    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    public void update() {
        unloadingTime++;
    }
    public void updateTimeQuantum() {
        timeQuantum++;
    }
    public int getExpectedTime() {
        return expectedTime;
    }
    public String getRockName() {
        return rockName;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getUnloadingTime() {
        return unloadingTime;
    }
    @Override
    public String toString() {
        return "<" +
                number + " " +
                rockName + " " +
                weight + " " +
                quantity + " " +
                "[" + expectedTime + "]" +
                ">";
    }
}
