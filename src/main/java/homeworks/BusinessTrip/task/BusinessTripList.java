package homeworks.BusinessTrip.task;

import java.util.Objects;

public class BusinessTripList {
    private final BusinessTrip[] trips;

    public BusinessTripList(BusinessTrip[] trips) {
        this.trips = trips;
    }

    public void show() {
        for (BusinessTrip trip : trips) {
            if (Objects.nonNull(trip)) {
                trip.show();
            }
        }
    }

    public void print() {
        System.out.println("Account;Transportation expenses;Days;Total cost");

        for (BusinessTrip trip : trips) {
            if (Objects.nonNull(trip)) {
                System.out.println(trip);
            }
        }
    }

    public BusinessTrip getWithMaximumCost() {
        int max = 0;
        BusinessTrip maxTrip = null;

        for (BusinessTrip trip : trips) {
            if (Objects.nonNull(trip) && trip.getTotal() >= max) {
                maxTrip = trip;
                max = trip.getTotal();
            }
        }
        return maxTrip;
    }

    public int getTwoFirstTotalDuration() {
        int total = 0;

        for (int i = 0; i < 1; i++) {
            BusinessTrip trip = trips[i];

            if (Objects.nonNull(trip)) {
                total += trip.getDays();
            }
        }

        return total;
    }

    public BusinessTrip getLast() {
        if (trips.length > 0) {
            return trips[trips.length - 1];
        }
        return null;
    }
}