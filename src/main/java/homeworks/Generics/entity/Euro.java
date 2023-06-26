package homeworks.Generics.entity;

public class Euro implements Comparable<Euro> {

    private int cents;

    public Euro(int cents) {
        this.cents = cents;
    }

    public Euro(Euro other) {
        this(other.cents);
    }

    public Euro minus(Euro other) {
        this.cents -= other.cents;
        return this;
    }

    public Euro minus(double value) {
        this.cents -= value;
        return this;
    }

    public Euro times(double value) {
        this.cents = (int) Math.round(this.cents * value);
        return this;
    }

    public Euro divideBy(double value) {
        this.cents = (int) Math.ceil(this.cents / value);
        return this;
    }

    public boolean moreThan(int value) {
        return this.cents > value;
    }

    public boolean lessThan(int value) {
        return this.cents < value;
    }

    public Euro abs() {
        return new Euro(Math.abs(this.cents));
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