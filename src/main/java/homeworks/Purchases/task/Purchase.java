package homeworks.Purchases.task;

public class Purchase implements Comparable<Purchase> {

    public static final String PRODUCT_NAME = "Computer";
    public static final double PRICE = 100.0;
    private int numberOfUnits;
    private double discountPercent;
    private WeekDay weekDay;

    public Purchase() {
    }

    public Purchase(int numberOfUnits, double discountPercent, WeekDay weekDay) {
        this.numberOfUnits = numberOfUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    }

    public Purchase(int numberOfUnits, double discountPercent, int weekDay) {
        this(numberOfUnits, discountPercent, WeekDay.values()[weekDay]);
    }

    // Getters and Setters
    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public int getCost() {
        double cost = PRICE * numberOfUnits * (100 - discountPercent) / 100;
        return (int) Math.round(cost / 100) * 100;
    }

    @Override
    public String toString() {
        return numberOfUnits + ";" + discountPercent + ";" + weekDay + ";" + CostUtils.formatCost(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return Integer.compare(this.numberOfUnits, purchase.numberOfUnits);
    }
}