package entity.blprnts;

import lombok.Data;

@Data
public abstract class Person {
    private int id;
    private String name;
    private int passport;
}
