package entity;

import entity.blprnts.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger  extends Person {
    private int id;
    private String name;
    private int passport;   //TODO сделать строкой

}
