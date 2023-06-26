package homeworks.Generics;

import homeworks.Generics.entity.Euro;
import homeworks.Generics.entity.Item;

public class Purchase<I extends Item, N extends Number> {
    private I item;
    private N quantity;

    public Purchase(I item, N quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public I getItem() {
        return this.item;
    }

    public N getQuantity() {
        return this.quantity;
    }

    public Euro calculateCost() {
        Euro price = item.calculatePrice();
        double quantityValue = quantity.doubleValue();

        return new Euro(price).times(quantityValue);
    }
}