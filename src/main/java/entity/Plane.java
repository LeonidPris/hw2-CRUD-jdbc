package entity;

import lombok.Data;

import java.util.List;

@Data
public class Plane {
    private int id;
    private String flight;
    List<Ticket> tickets;

    public Plane(int id, String flight, List<Ticket> tickets) {
        this.id = id;
        this.flight = flight;
        this.tickets = tickets;
    }

    public Plane(String flight, List<Ticket> tickets) {
        this.flight = flight;
        this.tickets = tickets;
    }
    public Plane(int id, String flight) {
        this.id = id;
        this.flight = flight;
        this.tickets = null;
    }


}
