package controllers;


import entity.Passenger;
import service.PassengerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/pass")
public class PassengerController extends HttpServlet {
    private  PassengerService passengerService;

//    public PassengerController() {
//        this.passengerService = new PassengerService();
//    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.passengerService = new PassengerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("inside DO GET");
        System.out.println(request.getParameter("id"));

        String id = request.getParameter("id");
        Passenger passenger = passengerService.findById(Integer.parseInt(id));
        if(passenger != null){
            System.out.println(passenger);
        }

    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
