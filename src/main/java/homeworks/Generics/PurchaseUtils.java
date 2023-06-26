package homeworks.Generics;

import homeworks.Generics.entity.Euro;
import homeworks.Generics.entity.Item;

import java.util.List;

public class PurchaseUtils<I extends Item, N extends Number> {
    private Purchase<I, N> purchase;

    public PurchaseUtils(Purchase<I, N> purchase) {
        this.purchase = purchase;
    }

    public Purchase<I, N> getPurchase() {
        return this.purchase;
    }

    public void printPurchase() {
        System.out.println("Item: " + purchase.getItem() + ", Quantity: " + purchase.getQuantity());
    }

    public void printCost() {
        System.out.println("Cost = " + purchase.calculateCost() + " Euro");
    }

    public void printCostDiff(PurchaseUtils<I, N> otherPurchaseUtils) {
        Euro diff = purchase.calculateCost().minus(otherPurchaseUtils.getPurchase().calculateCost());
        String sign = diff.moreThan(0) ? "positive " : diff.lessThan(0) ? "negative " : "";
        System.out.println(sign + "diff = " + diff.abs().toString() + " Euro");
    }

    public void printIsSameCost(List<Purchase<?, N>> purchases) {
        Euro thisCost = purchase.calculateCost();
        boolean foundMatch = false;

        for (Purchase<?, N> p : purchases) {
            if (p.calculateCost().equals(thisCost)) {
                System.out.println(p.getItem() + " has the same cost as " + purchase.getItem());
                foundMatch = true;
            }
        }

        if (!foundMatch) {
            System.out.println("There are no purchases with the same cost as " + purchase.getItem());
        }
    }
}