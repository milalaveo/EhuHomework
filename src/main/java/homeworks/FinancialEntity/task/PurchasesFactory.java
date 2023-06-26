package homeworks.FinancialEntity.task;

import java.util.Scanner;

public class PurchasesFactory {

    private enum PurchaseKind {
        GENERAL_PURCHASE, DISCOUNT_PER_UNIT, BULK_DISCOUNT
    }

    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        String id = scanner.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);

        switch (kind) {
            case GENERAL_PURCHASE:
                return new Purchase(scanner);
            case DISCOUNT_PER_UNIT:
                return new DiscountPerUnitPurchase(scanner);
            case BULK_DISCOUNT:
                return new BulkDiscountPurchase(scanner);
            default:
                throw new IllegalArgumentException();
        }
    }
}