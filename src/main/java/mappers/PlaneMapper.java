package mappers;

import entity.Plane;
import entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneMapper{
    public static Plane map(ResultSet resultSet) throws SQLException {
        Plane plane =  new Plane();
        plane.setId(resultSet.getInt("plane_id"));
        plane.setFlight(resultSet.getString("flight"));
        return plane;
    }


}
