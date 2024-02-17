package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Plane;
import entity.Ticket;
import repositories.PlaneRepository;
import repositories.TicketRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PlaneService implements IService<Plane>{
    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;

    public PlaneService() {
        this.ticketRepository = new TicketRepository();
        this.planeRepository = new PlaneRepository();
    }

    @Override
    public void createNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper om = new ObjectMapper();
        Plane plane = om.readValue(json, Plane.class);
        if (planeRepository.create(plane)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    @Override
    public String findById(int id) throws JsonProcessingException {
        List<Ticket> tickets = ticketRepository.readAllWithPlane(id);
        Plane plane = planeRepository.findById(id);
        plane.setTickets(tickets);
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(plane);
    }

    @Override
    public void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper om = new ObjectMapper();
        Plane plane = om.readValue(json, Plane.class);
        if (planeRepository.update(plane)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (planeRepository.deleteById(Integer.parseInt(id))) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
