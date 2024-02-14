package mappers;

import entity.Plane;
import entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneMapper{
    public static Plane map(ResultSet resultSet) throws SQLException {
        return new Plane(resultSet.getInt("plane_id"), resultSet.getString("flight"));
    }


}
