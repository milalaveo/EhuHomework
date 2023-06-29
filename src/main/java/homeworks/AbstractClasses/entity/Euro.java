package homeworks.AbstractClasses.entity;

public class Euro implements Comparable<Euro> {

    private final int cents;

    public Euro() {
        this.cents = 0;
    }

    public Euro(int cents) {
        this.cents = cents;
    }

    public Euro(Euro euro) {
        this(euro.cents);
    }

    public Euro plus(Euro other) {
        return new Euro(this.cents + other.cents);
    }

    public Euro minus(Euro other) {
        return new Euro(this.cents - other.cents);
    }

    public Euro times(Euro other) {
        return new Euro(this.cents * other.cents);
    }

    public Euro times(int value) {
        return new Euro(this.cents * value);
    }

    public Euro times(double value) {
        return new Euro((int) Math.round(this.cents * value));
    }

    @Override
    public String toString() {
        return String.format("%.2f", cents / 100.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Euro euro = (Euro) obj;
        return cents == euro.cents;
    }

    @Override
    public int compareTo(Euro euro) {
        return Integer.compare(this.cents, euro.cents);
    }
}