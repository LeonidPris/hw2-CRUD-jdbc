package controllers;

import service.PlaneService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaneController extends HttpServlet {
    private PlaneService planeService;

    public PlaneController() {
        this.planeService = new PlaneService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }
}
