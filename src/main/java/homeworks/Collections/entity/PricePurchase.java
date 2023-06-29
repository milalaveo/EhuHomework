package homeworks.Collections.entity;

public class PricePurchase extends Purchase {
    private final Euro discountEuro;

    public PricePurchase() {
        super();
        this.discountEuro = new Euro();
    }

    public PricePurchase(String name, Euro price, int number, Euro discountEuro) {
        super(name, price, number);
        this.discountEuro = discountEuro;
    }

    @Override
    public Euro getCost() {
        Euro discount = discountEuro.times(getUnits());
        return super.getCost().minus(discount);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountEuro;
    }
}