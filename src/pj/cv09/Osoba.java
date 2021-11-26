package pj.cv09;

import java.io.Serializable;

public class Osoba implements Serializable {
    private static String name, lastname;
    Datum date;

    Osoba (String name, String lastname, Datum date) {
        this.name = name;
        this.lastname = lastname;
        this.date = date;
    }

    @Override
    public String toString() {
        return (this.name + " " + this.lastname + " " + date);
    }
}
