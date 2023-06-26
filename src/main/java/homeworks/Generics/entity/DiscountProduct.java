package homeworks.Generics.entity;

public class DiscountProduct extends Product {
    private double discount;

    public DiscountProduct(String name, Euro price, double discount) {
        super(name, price);
        this.discount = discount;
    }

    @Override
    public Euro calculatePrice() {
        return super.calculatePrice().minus(this.discount);
    }
}