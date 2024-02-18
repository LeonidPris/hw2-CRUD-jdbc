package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import dto.PersonDTO;
import entity.Passenger;
import entity.blprnts.Person;
import repositories.PassengerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class PassengerService implements IService {
    private final PassengerRepository passengerRepository;

    public PassengerService() {
        passengerRepository = new PassengerRepository();
    }

    @Override
    public void createNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper om = new ObjectMapper();
        Passenger passenger = om.readValue(json, Passenger.class);
        if (passengerRepository.create(passenger)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public String findById(int id) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Passenger passenger = passengerRepository.findById(id);
        if (passenger != null) {
            return om.writeValueAsString(passenger);//TODO выбрось Exception в try/catch и обработай, чтоб не возвращать null
        }
        return null;
    }

    @Override
    public void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException { // если DTO без id ничего не найдет -> false
        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper om = new ObjectMapper();
        PersonDTO personDTO = om.readValue(json, PersonDTO.class);
        if (passengerRepository.update(personDTO)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (passengerRepository.deleteById(Integer.parseInt(id))) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
