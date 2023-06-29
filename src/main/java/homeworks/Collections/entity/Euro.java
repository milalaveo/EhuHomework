package homeworks.Collections.entity;

import java.util.Scanner;

public class Euro implements Comparable<Euro> {

    private int cents;

    public Euro() {
        this.cents = 0;
    }

    public Euro(int cents) {
        this.cents = cents;
    }

    public Euro(Euro euro) {
        this(euro.cents);
    }

    public Euro(Scanner scanner) {
        this(scanner.nextInt());
    }

    public Euro plus(Euro other) {
        this.cents += other.cents;
        return this;
    }

    public Euro minus(Euro other) {
        this.cents -= other.cents;
        return this;
    }

    public Euro times(Euro other) {
        this.cents *= other.cents;
        return this;
    }

    public Euro times(int value) {
        this.cents *= value;
        return this;
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
    public int hashCode() {
        return cents;
    }

    @Override
    public int compareTo(Euro euro) {
        return Integer.compare(this.cents, euro.cents);
    }
}