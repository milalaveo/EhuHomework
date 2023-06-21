package homeworks.Purchases.task;

public final class CostUtils {

    public static String formatCost(int number) {
        return String.format("%d.%02d", number / 100, number % 100);
    }

    public static String formatCost(double number) {
        return String.format("%.3f", number / 100);
    }
}