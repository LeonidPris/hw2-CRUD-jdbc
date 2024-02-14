package mappers;

import entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper {
    public static Ticket map(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getInt("ticket_id"),
                resultSet.getInt("person_id"),
                resultSet.getString("place"),
                resultSet.getInt("plane_id")
        );
    }
}
