package mappers;

import entity.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerMapper{
    public static Passenger map(ResultSet resultSet) throws SQLException {
        return new Passenger()
                .setId(resultSet.getInt("person_id"))
                .setName(resultSet.getString("name"))
                .setPassport(resultSet.getInt("passport"));
    }
}
