public class Wheelbarrow {
    private int number;
    private String rockName;
    private int weight;
    private int quantity;
    private int expectedTime;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getRockName() {
        return rockName;
    }

    public void setRockName(String rockName) {
        this.rockName = rockName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
