package homeworks.Purchases;

import homeworks.Purchases.task.Purchase;
import homeworks.Purchases.task.WeekDay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        int PURCHASES_NUMBER = 10;
        Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

        // Load data from file
        try (Scanner sc = new Scanner(new File("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);

            PURCHASES_NUMBER = sc.nextInt();
            for (int i = 0; i < PURCHASES_NUMBER; i++) {
                String productName = sc.next();
                double price = sc.nextDouble();
                int numberOfUnits = sc.nextInt();
                double discountPercent = sc.nextDouble();
                WeekDay weekDay = WeekDay.values()[sc.nextInt()];

                purchases[i] = new Purchase(productName, price, numberOfUnits, discountPercent, weekDay);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Output purchases
        for (int i = 0; i < PURCHASES_NUMBER; i++) {
            System.out.println(purchases[i].toString());
        }

        // Calculate average cost
        double totalCost = 0;
        for (Purchase purchase : purchases) {
            totalCost += purchase.getCost();
        }
        double averageCost = totalCost / PURCHASES_NUMBER;
        System.out.printf("Average cost: %.3f%n", averageCost);

        // Calculate total cost on Monday
        double totalMondayCost = 0;
        for (Purchase purchase : purchases) {
            if (purchase.getWeekDay() == WeekDay.MONDAY) {
                totalMondayCost += purchase.getCost();
            }
        }
        System.out.printf("Total cost on Monday: %.2f%n", totalMondayCost);

        // Find the day with the maximum purchase cost
        double maxCost = 0;
        WeekDay maxCostDay = null;
        for (Purchase purchase : purchases) {
            if (purchase.getCost() > maxCost) {
                maxCost = purchase.getCost();
                maxCostDay = purchase.getWeekDay();
            }
        }
        System.out.printf("Day with maximum purchase cost: %s%n", maxCostDay);

        // Sort the array by the number of purchased units in ascending order
        Arrays.sort(purchases);

        // Output sorted purchases
        for (int i = 0; i < PURCHASES_NUMBER; i++) {
            System.out.println(purchases[i].toString());
        }

        // Find a purchase with the number of units equal to 5
        Purchase key = new Purchase();
        key.setNumberOfUnits(5);
        int index = Arrays.binarySearch(purchases, key);

        if (index >= 0) {
            System.out.println("Purchase with number equal to 5: " + purchases[index]);
        } else {
            System.out.println("No purchase with number equal to 5 was found.");
        }
    }
}