package homeworks.FinancialEntity.task;

import java.util.Scanner;

public class BulkDiscountPurchase extends Purchase {

    private static final int DISCOUNT_THRESHOLD = 5;
    private double discountRate;

    public BulkDiscountPurchase() {
        super();
    }

    public BulkDiscountPurchase(String productName, Euro price, int units, double discountRate) {
        super(productName, price, units);
        this.discountRate = discountRate;
    }

    public BulkDiscountPurchase(Scanner scanner) {
        super(scanner);
        this.discountRate = scanner.nextDouble();
    }

    @Override
    public Euro getCost() {
        Euro cost = super.getCost();
        if (getUnits() > DISCOUNT_THRESHOLD) {
            cost.times(1 - discountRate / 100);
        }
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BulkDiscountPurchase)) return false;
        if (!super.equals(obj)) return false;
        BulkDiscountPurchase that = (BulkDiscountPurchase) obj;
        return Double.compare(that.discountRate, discountRate) == 0;
    }
}
