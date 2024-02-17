package controllers;

import service.PassengerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/passenger")
public class PassengerController extends HttpServlet {
    private PassengerService passengerService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.passengerService = new PassengerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String passenger = passengerService.findById(Integer.parseInt(id));

        if(passenger != null){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(passenger);
            writer.flush();
            response.setStatus(HttpServletResponse.SC_FOUND);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws  IOException {
        passengerService.createNew(request, response);
    }

    @Override
    protected void doPut (HttpServletRequest request, HttpServletResponse response) throws IOException {
        passengerService.updateData(request, response);
    }

    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response){
        passengerService.delete(request, response);
    }
}
