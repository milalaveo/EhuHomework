package FinancialEntity;

import homeworks.FinancialEntity.task.Euro;
import homeworks.FinancialEntity.task.BulkDiscountPurchase;
import homeworks.FinancialEntity.task.DiscountPerUnitPurchase;
import homeworks.FinancialEntity.task.Purchase;
import homeworks.FinancialEntity.task.PurchasesFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.*;

public class TestRunner {
    @Test
    public void testEuroDefaultConstructor() {
        Euro defaultEuro = new Euro();
        Euro expectedEuro = new Euro(0);
        Assert.assertEquals(expectedEuro, defaultEuro);
        Assert.assertEquals(expectedEuro.toString(), defaultEuro.toString());
    }

    @Test
    public void testEuroParametrizedConstructors() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(new Euro(123));
        Assert.assertEquals(euro1, euro2);
        Assert.assertEquals(euro1.toString(), euro2.toString());
    }

    @Test
    public void testEuroPlus() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(456);
        euro1.plus(euro2);
        Assert.assertEquals(new Euro(579), euro1);
    }

    @Test
    public void testEuroMinus() {
        Euro euro1 = new Euro(321);
        Euro euro2 = new Euro(123);
        euro1.minus(euro2);
        Assert.assertEquals(new Euro(198), euro1);
    }

    @Test
    public void testEuroTimes() {
        Euro euro = new Euro(123);
        euro.times(2);
        Assert.assertEquals(new Euro(246), euro);
    }

    @Test
    public void testEuroTimesClass() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(2);
        euro1.times(euro2);
        Assert.assertEquals(new Euro(246), euro1);
    }

    @Test
    public void testEuroToString() {
        Assert.assertEquals("0.00", new Euro().toString());
        Assert.assertEquals("0.01", new Euro(1).toString());
        Assert.assertEquals("0.12", new Euro(12).toString());
        Assert.assertEquals("12.34", new Euro(1234).toString());
    }

    @Test
    public void testEuroEquals() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(123);
        Euro euro3 = new Euro(456);
        Assert.assertEquals(euro1, euro2);
        Assert.assertNotEquals(euro1, euro3);
    }

    @Test
    public void testEuroCompareTo() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(456);
        Euro euro3 = new Euro(456);
        Assert.assertTrue(euro1.compareTo(euro2) < 0);
        Assert.assertTrue(euro2.compareTo(euro1) > 0);
        Assert.assertEquals(0, euro2.compareTo(euro3));
    }

    @Test
    public void testPurchasesDefaultConstructors() {
        Purchase[] purchases = new Purchase[]{
                new Purchase(),
                new DiscountPerUnitPurchase(),
                new BulkDiscountPurchase()
        };
        for (Purchase purchase : purchases) {
            Assert.assertNull(purchase.getProductName());
            Assert.assertNull(purchase.getPrice());
            Assert.assertEquals(0, purchase.getUnits());
        }
    }

    @Test
    public void testPurchasesParametrizedConstructors() {
        Purchase[] purchases = new Purchase[]{
                new Purchase("Banana", new Euro(100), 20),
                new DiscountPerUnitPurchase("Banana", new Euro(100), 20, new Euro(50)),
                new BulkDiscountPurchase("Banana", new Euro(100), 20, 5.825)
        };
        for (Purchase purchase : purchases) {
            Assert.assertEquals("Banana", purchase.getProductName());
            Assert.assertEquals(new Euro(100), purchase.getPrice());
            Assert.assertEquals(20, purchase.getUnits());
        }
    }

    @Test
    public void testPurchaseGetCost() {
        Euro price = new Euro(123);
        Purchase purchase = new Purchase("Banana", price, 20);
        Assert.assertEquals(new Euro(2460), purchase.getCost());
        // check that original price hasn't changed
        Assert.assertEquals(new Euro(123), price);
    }

    @Test
    public void testDiscountPerUnitPurchaseGetCost() {
        Euro price = new Euro(300);
        Purchase purchase = new DiscountPerUnitPurchase("Banana", price, 2, new Euro(50));
        Assert.assertEquals(new Euro(500), purchase.getCost());
    }

    @Test
    public void testBulkDiscountPurchaseGetCost() {
        Euro price1 = new Euro(500);
        Euro price2 = new Euro(500);
        Purchase purchase1 = new BulkDiscountPurchase("Banana", price1, 10, 5.825);
        Purchase purchase2 = new BulkDiscountPurchase("Banana", price2, 5, 5.825);
        Assert.assertEquals(new Euro(4709), purchase1.getCost());
        Assert.assertEquals(new Euro(2500), purchase2.getCost());
    }

    @Test
    public void testPurchasesEquals() {
        Purchase purchase = new Purchase("Banana", new Euro(100), 20);
        Purchase discountPerUnitPurchase = new DiscountPerUnitPurchase("Banana", new Euro(100), 30, new Euro(60));
        Purchase bulkDiscountPurchase = new BulkDiscountPurchase("Banana", new Euro(100), 40, 12.34);
        Assert.assertEquals(purchase, discountPerUnitPurchase);
        Assert.assertEquals(purchase, bulkDiscountPurchase);

        Purchase anotherPurchase = new Purchase("Banana", new Euro(200), 20);
        Assert.assertNotEquals(purchase, anotherPurchase);
        Assert.assertNotEquals(discountPerUnitPurchase, anotherPurchase);
        Assert.assertNotEquals(bulkDiscountPurchase, anotherPurchase);
    }

    @Test
    public void testPurchaseFactory() {
        String input = "BULK_DISCOUNT Banana 500 20 5.825";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        Purchase testPurchase = PurchasesFactory.getPurchaseFromFactory(scanner);
        Assert.assertTrue(testPurchase instanceof BulkDiscountPurchase);
    }
}