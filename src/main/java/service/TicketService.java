package service;

import entity.Ticket;
import repositories.TicketRepository;

public class TicketService implements IService<Ticket> {
    private final TicketRepository ticketRepository;

    public TicketService() {
        this.ticketRepository = new TicketRepository();
    }

    @Override
    public boolean createNew(Ticket entity) {
        return ticketRepository.create(entity);
    }

    @Override
    public Ticket findById(int id) {
        return ticketRepository.read(id);
    }

    @Override
    public boolean updateData(Ticket entity) {
        return ticketRepository.update(entity);
    }

    @Override
    public boolean deleteById(int id) {
        return ticketRepository.delete(id);
    }
}
