package homeworks.AbstractClasses;

import homeworks.AbstractClasses.entity.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        Product product = new Product("Milk", new Euro(250));

        AbstractPurchase[] purchases = {
                new UnitDiscountPurchase(product, 10, new Euro(20)),
                new UnitDiscountPurchase(product, 5, new Euro(10)),
                new BulkDiscountPurchase(product, 15, 10),
                new BulkDiscountPurchase(product, 8, 10),
                new TransportAdditionPurchase(product, 7, new Euro(300)),
                new TransportAdditionPurchase(product, 4, new Euro(200))
        };

        printPurchases(purchases);
        Arrays.sort(purchases);

        printPurchases(purchases);
        System.out.println("Minimum cost = " + purchases[purchases.length - 1].getCost());

        int foundIndex = search(purchases, new Euro(500));
        if (foundIndex >= 0) {
            System.out.println("Found purchase: " + purchases[foundIndex]);
        } else {
            System.out.println("Purchase with cost 5.00 Euro not found.");
        }
    }

    private static void printPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    public static int search(AbstractPurchase[] purchases, Euro cost) {
        Product keyProduct = new Product("Key", cost);
        UnitDiscountPurchase keyPurchase = new UnitDiscountPurchase(keyProduct, 1, new Euro(0));
        return Arrays.binarySearch(purchases, keyPurchase);
    }
}
