package homeworks.Collections;

import homeworks.Collections.entity.Euro;
import homeworks.Collections.entity.PricePurchase;
import homeworks.Collections.entity.Purchase;
import homeworks.Collections.entity.WeekDay;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static homeworks.Collections.entity.WeekDay.findWeekDay;

public class Runner {
    public static void main(String[] args) {
        Map<Purchase, WeekDay> firstPurchaseMap = new LinkedHashMap<>();
        Map<Purchase, WeekDay> lastPurchaseMap = new LinkedHashMap<>();
        List<PricePurchase> pricePurchases = new ArrayList<>();
        Map<WeekDay, List<Purchase>> purchasesByDay = new HashMap<>();

        try (Scanner scanner = new Scanner(new FileReader("src/main/java/homeworks/Collections/in.csv"))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                WeekDay weekDay = findWeekDay(scanner.nextLine());

                String[] purchaseData = line.split(";");

                Purchase purchase;
                if (purchaseData.length == 4) {
                    purchase = new PricePurchase(purchaseData[0], new Euro(Integer.parseInt(purchaseData[1])),
                            Integer.parseInt(purchaseData[2]), new Euro(Integer.parseInt(purchaseData[3])));

                    // 10. load instances of the subclass PricePurchase from the file in.csv into the list (List<PricePurchase>);
                    pricePurchases.add((PricePurchase) purchase);
                } else {
                    purchase = new Purchase(purchaseData[0], new Euro(Integer.parseInt(purchaseData[1])),
                            Integer.parseInt(purchaseData[2]));
                }

                // 1. load the content of the file in.csv into the map where a purchase is a key and a weekday of last purchase is a value;
                lastPurchaseMap.put(purchase, weekDay);

                // 3. load the content of the file in.csv into the map where a purchase is a key and a weekday of first purchase is a value;
                if (!firstPurchaseMap.containsKey(purchase)) {
                    firstPurchaseMap.put(purchase, weekDay);
                }

                // 12. load the content of the file in.csv into the enumerated map where a weekday is a key and purchases list for this weekday is a value;
                if (!purchasesByDay.containsKey(weekDay)) {
                    purchasesByDay.put(weekDay, new ArrayList<Purchase>());
                }
                purchasesByDay.get(weekDay).add(purchase);
            }

            // 2. print lastPurchaseMap by for–each cycle;
            printMap(lastPurchaseMap);
            // 4. print firstPurchaseMap by for–each cycle;
            printMap(firstPurchaseMap);

            // 7. remove all entries from the first map where the purchase name is 'meat';
            for (Iterator<Map.Entry<Purchase, WeekDay>> it = firstPurchaseMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Purchase, WeekDay> entry = it.next();
                if (entry.getKey().getProductName().equals("meat")) {
                    it.remove();
                }
            }

            // 8. remove all entries from the lastPurchaseMap on FRIDAY;
            for (Iterator<Map.Entry<Purchase, WeekDay>> it = lastPurchaseMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Purchase, WeekDay> entry = it.next();
                if (entry.getValue().equals(WeekDay.FRIDAY)) {
                    it.remove();
                }
            }

            // 9. print firstPurchaseMap and lastPurchaseMap maps by for–each cycle;
            printMap(firstPurchaseMap);
            printMap(lastPurchaseMap);

            // 5. find the first and the last weekdays for bread with price 1.55;
            System.out.println(findInMap(firstPurchaseMap, "bread", new Euro(155)));
            // 6. find the first weekday for bread with price 1.70;
            System.out.println(findInMap(firstPurchaseMap, "bread", new Euro(170)));

            // 13. print the purchasesByDay by for–each cycle;
            printMap(purchasesByDay);

            // 15. find all purchases on MONDAY
            List<Purchase> purchasesOnMonday = purchasesByDay.get(WeekDay.MONDAY);
            System.out.println("Purchases on " + WeekDay.MONDAY + " => " + purchasesOnMonday);

            // 14. print the total cost of all purchases for each weekday
            for (Map.Entry<WeekDay, List<Purchase>> entry : purchasesByDay.entrySet()) {
                System.out.println(entry.getKey() + " => " + totalCost(entry.getValue()));
            }

            // 11. print the total cost of priceDiscountPurchases;
            System.out.println("Total cost of PricePurchase: " + totalCost(pricePurchases));
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }

    private static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    private static WeekDay findInMap(Map<Purchase, WeekDay> map, String product, Euro price) {
        for (Map.Entry<Purchase, WeekDay> entry : map.entrySet()) {
            if (entry.getKey().getProductName().equals(product) && entry.getKey().getPrice().equals(price)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static <P extends Purchase> Euro totalCost(List<P> list) {
        Euro totalCost = new Euro();
        for (P purchase : list) {
            totalCost.plus(new Euro(purchase.getPrice()).times(purchase.getUnits()));
        }

        return totalCost;
    }
}