package service;

import entity.Plane;
import entity.Ticket;
import repositories.PlaneRepository;
import repositories.TicketRepository;

import java.util.List;

public class PlaneService implements IService<Plane>{
    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;


    public PlaneService() {
        this.ticketRepository = new TicketRepository();
        this.planeRepository = new PlaneRepository();
    }

    @Override
    public boolean createNew(Plane entity) {
        return planeRepository.create(entity);
    }

    @Override
    public Plane findById(int id) {
        List<Ticket> tickets = ticketRepository.readAllWithPlane(id);
        Plane plane = planeRepository.read(id);
        plane.setTickets(tickets);
        return plane;
    }

    @Override
    public boolean updateData(Plane entity) {
        return planeRepository.update(entity);
    }

    @Override
    public boolean deleteById(int id) {
        return planeRepository.delete(id);
    }
}
