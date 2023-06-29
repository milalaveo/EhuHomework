package homeworks.Collections.entity;

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
    protected String fieldsToString() {
        return getClass().getSimpleName() + ";" + getProductName() + ";" + getPrice() + ";" + getUnits();
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
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