package homeworks.Generics.entity;

public class Product extends Item {
    private Euro price;

    public Product(String name, Euro price) {
        super(name);
        this.price = price;
    }

    @Override
    public Euro calculatePrice() {
        return this.price;
    }
}