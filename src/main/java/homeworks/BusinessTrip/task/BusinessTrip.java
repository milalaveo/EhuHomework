package homeworks.BusinessTrip.task;

public class BusinessTrip {
    private String employeeAccount;
    private int transportationExpenses;
    private int days;
    private static final int DAILY_ALLOWANCE_RATE = 10000;

    public BusinessTrip() {
    }

    public BusinessTrip(String employeeAccount, int transportationExpenses, int days) {
        checkTransportationExpenses(transportationExpenses);
        checkDays(days);

        this.employeeAccount = employeeAccount;
        this.transportationExpenses = transportationExpenses;
        this.days = days;
    }

    public int getTotal() {
        return transportationExpenses + days * DAILY_ALLOWANCE_RATE;
    }

    public void show() {
        System.out.println(
                "rate = " + getEuros(DAILY_ALLOWANCE_RATE) +
                        "\naccount = " + employeeAccount +
                        "\ntransport = " + getEuros(transportationExpenses) +
                        "\ndays = " + days +
                        "\ntotal = " + getEuros(getTotal())
        );
    }

    public void setEmployeeAccount(String employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public void setTransportationExpenses(int transportationExpenses) {
        checkTransportationExpenses(transportationExpenses);
        this.transportationExpenses = transportationExpenses;
    }

    private static void checkTransportationExpenses(int transportationExpenses) {
        if (transportationExpenses <= 0) {
            throw new IllegalArgumentException("Transportation expenses must be a positive number");
        }
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        checkDays(days);
        this.days = days;
    }

    private static void checkDays(int daysNumber) {
        if (daysNumber <= 0) {
            throw new IllegalArgumentException("Number of days must be a positive number");
        }
    }

    private static String getEuros(int amount) {
        return String.format("%d.%02d", amount / 100, amount % 100);
    }

    @Override
    public String toString() {
        return String.join(";", employeeAccount, getEuros(transportationExpenses), String.valueOf(days), getEuros(getTotal()));
    }
}