package AbstractClasses;

import org.junit.*;
import homeworks.AbstractClasses.Runner;
import homeworks.AbstractClasses.entity.*;

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
    public void testUnitDiscountPurchase() {
        Product product = new Product("Milk", new Euro(250));
        AbstractPurchase purchase = new UnitDiscountPurchase(product, 10, new Euro(20));
        Assert.assertEquals(new Euro(2300), purchase.getCost());
    }

    @Test
    public void testBulkDiscountPurchase() {
        Product product = new Product("Milk", new Euro(250));
        AbstractPurchase purchase = new BulkDiscountPurchase(product, 15, 10);
        Assert.assertEquals(new Euro(3375), purchase.getCost());
    }

    @Test
    public void testTransportAdditionPurchase() {
        Product product = new Product("Milk", new Euro(250));
        AbstractPurchase purchase = new TransportAdditionPurchase(product, 7, new Euro(300));
        Assert.assertEquals(new Euro(2050), purchase.getCost());
    }

    @Test
    public void testSearch() {
        Product product = new Product("Milk", new Euro(250));
        AbstractPurchase[] purchases = {
                new UnitDiscountPurchase(product, 10, new Euro(20)),
                new UnitDiscountPurchase(product, 5, new Euro(10)),
                new BulkDiscountPurchase(product, 15, 10),
                new BulkDiscountPurchase(product, 8, 10),
                new TransportAdditionPurchase(product, 7, new Euro(300)),
                new TransportAdditionPurchase(product, 4, new Euro(200))
        };
        int foundIndex = Runner.search(purchases, new Euro(1200));
        Assert.assertTrue(foundIndex >= 0);
        Assert.assertEquals(new Euro(1200), purchases[foundIndex].getCost());
    }
}
