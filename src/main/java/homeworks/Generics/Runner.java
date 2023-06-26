package homeworks.Generics;

import homeworks.Generics.entity.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        Purchase<Product, Number> p1 = new Purchase<>(new Product("Milk", new Euro(170)), 20);
        PurchaseUtils<Product, Number> pu1 = new PurchaseUtils<>(p1);
        pu1.printPurchase();
        pu1.printCost();

        Purchase<Product, Number> p2 = new Purchase<>(new Product("Sugar", new Euro(300)), 12.5);
        PurchaseUtils<Product, Number> pu2 = new PurchaseUtils<>(p2);
        pu2.printCost();
        pu2.printCostDiff(pu1);

        Purchase<DiscountProduct, Number> p3 = new Purchase<>(new DiscountProduct("Sugar", new Euro(280), 0.10), 60);

        PurchaseUtils<Service, Number> pu4 = new PurchaseUtils<>(new Purchase<>(new Service("Gym workout", new Euro(7560), 5), 2.25));
        Service service = pu4.getPurchase().getItem();
        System.out.println(service);
        pu4.printCost();

        pu2.printIsSameCost(Arrays.asList(p1, p3, pu4.getPurchase()));
    }
}
