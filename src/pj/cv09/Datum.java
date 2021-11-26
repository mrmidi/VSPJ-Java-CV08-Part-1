package pj.cv09;

import java.io.Serializable;

public class Datum implements Serializable {
    private static int day, month, year;

    public Datum(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return (day + "." + month + "." + year);
    }
}
