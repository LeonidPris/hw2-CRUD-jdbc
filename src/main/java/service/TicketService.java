package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PersonDTO;
import entity.Passenger;
import entity.Ticket;
import repositories.TicketRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class TicketService implements IService {
    private final TicketRepository ticketRepository;

    public TicketService() {
        this.ticketRepository = new TicketRepository();
    }

    @Override
    public void createNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper om = new ObjectMapper();
        Ticket ticket = om.readValue(json, Ticket.class);
        if (ticketRepository.create(ticket)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    @Override
    public String findById(int id) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Ticket ticket = ticketRepository.findById(id);
        if (ticket != null) {
            return om.writeValueAsString(ticket);
        }
        return null;
    }

    @Override
    public void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper om = new ObjectMapper();
        Ticket ticket = om.readValue(json, Ticket.class);
        if (ticketRepository.update(ticket)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (ticketRepository.deleteById(Integer.parseInt(id))) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
