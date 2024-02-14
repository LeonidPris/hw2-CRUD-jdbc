package dto;

import entity.blprnts.Person;
import lombok.Data;

@Data
public class PersonDTO extends Person {
    private int id;
    private String name;
    private int passport;

    public PersonDTO(int id, String name, int passport) {
        this.id = id;
        this.name = name;
        this.passport = passport;
    }

    public PersonDTO(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }
}
