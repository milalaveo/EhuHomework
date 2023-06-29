package homeworks.AbstractClasses.entity;

public class UnitDiscountPurchase extends AbstractPurchase {
    private final Euro discountPerUnit;

    public UnitDiscountPurchase() {
        super();
        this.discountPerUnit = new Euro();
    }

    public UnitDiscountPurchase(Product product, int numberOfUnits, Euro discountPerUnit) {
        super(product, numberOfUnits);
        this.discountPerUnit = discountPerUnit;
    }

    @Override
    protected Euro calculateCost() {
        return new Euro(getProduct().getPrice()).minus(discountPerUnit).times(getNumberOfUnits());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + discountPerUnit;
    }
}

