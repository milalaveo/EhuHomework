package homeworks.Purchases.task;

public class Purchase implements Comparable<Purchase> {

    private String productName;
    private double price;
    private int numberOfUnits;
    private double discountPercent;
    private WeekDay weekDay;

    public Purchase() {
    }

    public Purchase(String productName, double price, int numberOfUnits, double discountPercent, WeekDay weekDay) {
        this.productName = productName;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

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

    public double getCost() {
        return Math.round((price * numberOfUnits * (100 - discountPercent) / 100) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return productName + ";" + price + ";" + numberOfUnits + ";" + discountPercent + ";" + weekDay + ";" + getCost();
    }

    @Override
    public int compareTo(Purchase purchase) {
        return Integer.compare(this.numberOfUnits, purchase.numberOfUnits);
    }
}