package homeworks.FinancialEntity.task;

import java.util.Objects;
import java.util.Scanner;

public class Purchase {

    private String productName;
    private Euro price;
    private int units;

    public Purchase() {
    }

    public Purchase(String productName, Euro price, int units) {
        this.productName = productName;
        this.price = price;
        this.units = units;
    }

    public Purchase(Scanner scanner) {
        this.productName = scanner.next();
        this.price = new Euro(scanner);
        this.units = scanner.nextInt();
    }

    public String getProductName() {
        return productName;
    }

    public Euro getPrice() {
        return price;
    }

    public int getUnits() {
        return units;
    }

    public Euro getCost() {
        return new Euro(price).times(units);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ";" + getProductName() + ";" + getPrice() + ";" + getUnits() + ";" + getCost();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (!(obj instanceof Purchase)) return false;

        Purchase purchase = (Purchase) obj;

        boolean productNameIsEqual = Objects.equals(productName, purchase.productName);
        boolean priceIsEqual = Objects.equals(price, purchase.price);

        return productNameIsEqual && priceIsEqual;
    }
}
