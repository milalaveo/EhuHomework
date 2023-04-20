package homeworks.BusinessTrip;

import homeworks.BusinessTrip.task.BusinessTrip;

public class Runner {
    public static void main(String[] args) {
        BusinessTrip[] trips = new BusinessTrip[]{
                new BusinessTrip("Felicity Thompson", 200, 4),
                new BusinessTrip("Darius Montgomery", 150, 3),
                null,
                new BusinessTrip("Leona Gutierrez", 300, 7),
                new BusinessTrip("Warren O'Connell", 100, 5),
                new BusinessTrip()
        };

        System.out.println("All business trips:");
        for (BusinessTrip trip: trips) {
            if (trip != null) {
                trip.show();
            }
        }

        int max = 0;
        BusinessTrip maxTrip = null;

        for (BusinessTrip trip : trips) {
            if (trip != null && trip.getTotal() >= max) {
                maxTrip = trip;
                max = trip.getTotal();
            }
        }

        if (maxTrip != null) {
            System.out.println("Business trip with maximum cost:");
            maxTrip.show();
        }

        BusinessTrip lastTrip = trips[trips.length - 1];
        lastTrip.setEmployeeAccount("Catalina Vasquez");
        lastTrip.setTransportationExpenses(170);
        lastTrip.setDays(8);

        int totalDuration = trips[0].getDays() + trips[1].getDays();
        System.out.println("Total duration: " + totalDuration + " days\n");

        System.out.println("Account;Transportation expenses;Days;Total cost");

        for (BusinessTrip trip : trips) {
            System.out.println(trip);
        }
    }
}