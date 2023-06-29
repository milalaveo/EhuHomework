package homeworks.AbstractClasses.entity;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
    private int numberOfUnits;

    protected AbstractPurchase() {
        this.product = new Product("Default", new Euro());
        this.numberOfUnits = 0;
    }

    protected AbstractPurchase(Product product, int numberOfUnits) {
        this.product = product;
        this.numberOfUnits = numberOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Euro getCost() {
        return calculateCost();
    }

    protected abstract Euro calculateCost();

    @Override
    public String toString() {
        return getClass().getSimpleName() + ";" + getProduct() + ";" + getNumberOfUnits() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
        return -getCost().compareTo(purchase.getCost());
    }
}

