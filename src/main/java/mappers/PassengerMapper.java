package mappers;

import entity.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerMapper{
    public static Passenger map(ResultSet resultSet) throws SQLException {
        return new Passenger(resultSet.getInt("person_id"), resultSet.getString("name"), resultSet.getInt("passport"));
    }
}
