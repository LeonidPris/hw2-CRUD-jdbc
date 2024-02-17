package service;

import entity.Plane;
import entity.Ticket;
import repositories.PlaneRepository;
import repositories.TicketRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlaneService implements IService<Plane>{
    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;


    public PlaneService() {
        this.ticketRepository = new TicketRepository();
        this.planeRepository = new PlaneRepository();
    }

    @Override
    public void createNew(HttpServletRequest request, HttpServletResponse response) {
        // todo mapper
        // planeRepository.create(entity);
    }

    @Override
    public String findById(int id) {
        List<Ticket> tickets = ticketRepository.readAllWithPlane(id);
        Plane plane = planeRepository.findById(id);
        plane.setTickets(tickets);
        return plane.toString();
    }

    @Override
    public void updateData(HttpServletRequest request, HttpServletResponse response) {
        //return planeRepository.update(entity);
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        planeRepository.deleteById(Integer.parseInt(id));
    }
}
