package homeworks.FinancialEntity.task;

import java.util.Scanner;

public class DiscountPerUnitPurchase extends Purchase {

    private Euro discount;

    public DiscountPerUnitPurchase() {
        super();
    }

    public DiscountPerUnitPurchase(String productName, Euro price, int units, Euro discount) {
        super(productName, price, units);
        this.discount = discount;
    }

    public DiscountPerUnitPurchase(Scanner scanner) {
        super(scanner);
        this.discount = new Euro(scanner);
    }

    @Override
    public Euro getCost() {
        return new Euro(getPrice()).minus(discount).times(getUnits());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DiscountPerUnitPurchase)) return false;
        if (!super.equals(obj)) return false;
        DiscountPerUnitPurchase that = (DiscountPerUnitPurchase) obj;
        return discount.equals(that.discount);
    }
}
