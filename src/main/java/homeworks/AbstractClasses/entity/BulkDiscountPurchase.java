package homeworks.AbstractClasses.entity;

public class BulkDiscountPurchase extends AbstractPurchase {
    private static final int MIN_UNITS_FOR_DISCOUNT = 10;
    private final double discountPercent;

    public BulkDiscountPurchase() {
        super();
        this.discountPercent = 0;
    }

    public BulkDiscountPurchase(Product product, int numberOfUnits, double discountPercent) {
        super(product, numberOfUnits);
        this.discountPercent = discountPercent;
    }

    @Override
    protected Euro calculateCost() {
        if (getNumberOfUnits() > MIN_UNITS_FOR_DISCOUNT) {
            return new Euro(getProduct().getPrice()).times(1 - discountPercent / 100).times(getNumberOfUnits());
        }

        return new Euro(getProduct().getPrice()).times(getNumberOfUnits());
    }
}
