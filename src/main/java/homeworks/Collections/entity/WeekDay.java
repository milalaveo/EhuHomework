package homeworks.Collections.entity;

public enum WeekDay {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public static WeekDay findWeekDay(String weekday) {
        return valueOf(weekday.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
