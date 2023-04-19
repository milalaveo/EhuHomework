package homeworks.BusinessTrip;

import homeworks.BusinessTrip.task.BusinessTrip;
import homeworks.BusinessTrip.task.BusinessTripList;

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
        BusinessTripList tripList = new BusinessTripList(trips);

        System.out.println("All business trips:");
        tripList.show();

        System.out.println("Business trip with maximum cost:");
        tripList.getWithMaximumCost().show();

        BusinessTrip lastTrip = tripList.getLast();
        lastTrip.setEmployeeAccount("Catalina Vasquez");
        lastTrip.setTransportationExpenses(170);
        lastTrip.setDays(8);

        int totalDuration = tripList.getTwoFirstTotalDuration();
        System.out.println("Total duration: " + totalDuration + " days\n");

        tripList.print();
    }
}