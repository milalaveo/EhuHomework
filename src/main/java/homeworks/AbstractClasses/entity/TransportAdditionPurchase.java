package homeworks.AbstractClasses.entity;

public class TransportAdditionPurchase extends AbstractPurchase {
    private final Euro transportCost;

    public TransportAdditionPurchase() {
        super();
        this.transportCost = new Euro();
    }

    public TransportAdditionPurchase(Product product, int numberOfUnits, Euro transportCost) {
        super(product, numberOfUnits);
        this.transportCost = transportCost;
    }

    public Euro getTransportCost() {
        return transportCost;
    }

    @Override
    protected Euro calculateCost() {
        return new Euro(getProduct().getPrice()).times(getNumberOfUnits()).plus(getTransportCost());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + transportCost;
    }
}
