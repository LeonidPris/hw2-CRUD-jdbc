package entity;

import entity.blprnts.Person;
import lombok.Data;

@Data
public class Passenger  extends Person {
    private int id;
    private String name;
    private int passport;

    public Passenger(int id, String name, int passport) {
        this.id = id;
        this.name = name;
        this.passport = passport;
    }
    public Passenger(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }
}
