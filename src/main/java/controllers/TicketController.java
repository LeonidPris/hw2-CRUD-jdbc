package controllers;

import service.TicketService;

import javax.servlet.http.HttpServlet;

public class TicketController extends HttpServlet {

    TicketService ticketService;
    public TicketController() {
        ticketService = new TicketService();
    }
}
