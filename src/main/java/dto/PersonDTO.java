package dto;

import entity.blprnts.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO extends Person {
    private int id;
    private String name;
    private int passport;
}
