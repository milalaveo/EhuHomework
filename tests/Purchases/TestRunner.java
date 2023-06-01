package Purchases;

import homeworks.Purchases.task.Purchase;
import homeworks.Purchases.task.WeekDay;

import org.junit.*;

public class TestRunner {
    @Test
    public void testPurchaseConstructor() {
        Purchase purchase = new Purchase("Product", 100.0, 5, 10.0, WeekDay.MONDAY);
        Assert.assertEquals("Product", purchase.getProductName());
        Assert.assertEquals(100.0, purchase.getPrice(), 0.01);
        Assert.assertEquals(5, purchase.getNumberOfUnits());
        Assert.assertEquals(10.0, purchase.getDiscountPercent(), 0.01);
        Assert.assertEquals(WeekDay.MONDAY, purchase.getWeekDay());
    }

    @Test
    public void testPurchaseEmptyConstructor() {
        Purchase purchase = new Purchase();
        Assert.assertNull(purchase.getProductName());
        Assert.assertEquals(0.0, purchase.getPrice(), 0.01);
        Assert.assertEquals(0.0, purchase.getNumberOfUnits(), 0.01);
        Assert.assertEquals(0.0, purchase.getDiscountPercent(), 0.01);
        Assert.assertNull(purchase.getWeekDay());
    }

    @Test
    public void testGetCost() {
        Purchase purchase = new Purchase("Product", 100.0, 5, 10.0, WeekDay.MONDAY);
        double expectedCost = 450.0; // assuming the cost is calculated correctly
        Assert.assertEquals(expectedCost, purchase.getCost(), 0.01);
    }
}