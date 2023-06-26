package homeworks.FinancialEntity;

import homeworks.FinancialEntity.task.Euro;
import homeworks.FinancialEntity.task.Purchase;
import homeworks.FinancialEntity.task.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader("src/main/java/homeworks/FinancialEntity/in.txt"))) {
            scanner.useLocale(Locale.ENGLISH);

            final int PURCHASES_NUMBER = 6;
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

            Euro maxCost = new Euro();
            Purchase maxCostPurchase = null;
            boolean allPurchasesAreEqual = true;

            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(scanner);
                Purchase current = purchases[i];
                System.out.println(current);

                Euro cost = current.getCost();
                if (cost.compareTo(maxCost) > 0) {
                    maxCost = cost;
                    maxCostPurchase = current;
                }

                allPurchasesAreEqual = allPurchasesAreEqual && current.equals(purchases[0]);
            }
            System.out.println("\nPurchase with maximum cost:");
            System.out.println(maxCostPurchase);

            if (allPurchasesAreEqual) {
                System.out.println("\nAll purchases are equal");
            } else {
                System.out.println("\nAll purchases are NOT equal");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}