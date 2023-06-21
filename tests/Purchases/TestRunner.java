package Purchases;

import homeworks.Purchases.task.CostUtils;
import homeworks.Purchases.task.Purchase;
import homeworks.Purchases.task.WeekDay;

import org.junit.*;

public class TestRunner {
    @Test
    public void testPurchaseConstructor() {
        Purchase purchase = new Purchase(5, 10.0, WeekDay.MONDAY);
        Assert.assertEquals(5, purchase.getNumberOfUnits());
        Assert.assertEquals(10.0, purchase.getDiscountPercent(), 0.01);
        Assert.assertEquals(WeekDay.MONDAY, purchase.getWeekDay());
    }

    @Test
    public void testPurchaseEmptyConstructor() {
        Purchase purchase = new Purchase();
        Assert.assertEquals(0.0, purchase.getNumberOfUnits(), 0.01);
        Assert.assertEquals(0.0, purchase.getDiscountPercent(), 0.01);
        Assert.assertNull(purchase.getWeekDay());
    }

    @Test
    public void testPurchaseConstructorEquity() {
        Purchase purchase = new Purchase();
        Purchase emptyPurchase = new Purchase(0, 0.0, null);
        Assert.assertEquals(emptyPurchase.getNumberOfUnits(), purchase.getNumberOfUnits());
        Assert.assertEquals(emptyPurchase.getDiscountPercent(), purchase.getDiscountPercent(), 0.01);
        Assert.assertEquals(emptyPurchase.getWeekDay(), purchase.getWeekDay());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPurchaseConstructorInvalidWeekDay() {
        new Purchase(17, 20.0, 7);
    }

    @Test
    public void testGetCost() {
        Purchase purchase = new Purchase(5, 10.0, WeekDay.MONDAY);
        double expectedCost = 500; // assuming the cost is calculated correctly
        Assert.assertEquals(expectedCost, purchase.getCost(), 0.01);
    }

    @Test
    public void testGetCostCents() {
        Purchase purchase = new Purchase(90, 1.69, WeekDay.MONDAY);
        Assert.assertEquals(8800, purchase.getCost());
    }

    @Test
    public void testGetCostRoundingUp() {
        Purchase purchase = new Purchase(10, 0, WeekDay.MONDAY);
        Assert.assertEquals(1000, purchase.getCost());
    }

    @Test
    public void testGetCostRoundingDown() {
        Purchase purchase = new Purchase(13, 10, WeekDay.MONDAY);
        Assert.assertEquals(1200, purchase.getCost());
    }

    @Test
    public void testFormatEuro() {
        Assert.assertEquals("3.50", CostUtils.formatCost(350));
        Assert.assertEquals("3.05", CostUtils.formatCost(305));
        Assert.assertEquals("3.00", CostUtils.formatCost(300));
        Assert.assertEquals("0.05", CostUtils.formatCost(5));
        Assert.assertEquals("0.00", CostUtils.formatCost(0));
        Assert.assertEquals("1005.00", CostUtils.formatCost(100500));
    }
}