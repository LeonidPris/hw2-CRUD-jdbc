package entity;

import lombok.Data;

@Data
public class Ticket {
    private int id;
    private int personId;
    private String place;
    private int planeId;

    public Ticket(int personId, String place, int planeId) {
        this.personId = personId;
        this.place = place;
        this.planeId = planeId;
    }

    public Ticket(int id, int personId, String place, int planeId) {
        this.id = id;
        this.personId = personId;
        this.place = place;
        this.planeId = planeId;
    }
}
