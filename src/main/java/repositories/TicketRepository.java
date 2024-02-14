package repositories;

import connect.ConnectionDB;
import entity.Ticket;
import mappers.TicketMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository implements Repository<Ticket> {
    @Override
    public boolean create(Ticket entity) {
        int saveFlag;
        try (Connection connection = ConnectionDB.getConnection()) {
            String sql = "Insert Into Ticket(person_id, place, plane_id) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getPersonId());
            ps.setString(2, entity.getPlace());
            ps.setInt(3, entity.getPlaneId());
            saveFlag = ps.executeUpdate();

            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saveFlag > 0;
    }

    @Override
    public Ticket read(int id) {
        Ticket ticket = null;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("Select *  from Ticket  where ticket_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ticket = TicketMapper.map(rs);
            }

            connection.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public boolean update(Ticket entity) {
        int updateFlag = 0;
        try(Connection connection = ConnectionDB.getConnection()){
            Ticket ticket = null;
            String sql = "Select * from Ticket where ticket_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ticket = TicketMapper.map(rs);
            }

            if (ticket != null){
                String sqlUpd = "UPDATE Ticket SET place = ?, person_id = ?, plane_id = ? where ticket_id = ?";
                PreparedStatement psUpd = connection.prepareStatement(sqlUpd);
                psUpd.setString(1, entity.getPlace());
                psUpd.setInt(2, entity.getPersonId());
                psUpd.setInt(2, entity.getPlaneId());
                psUpd.setInt(3, ticket.getId());
                updateFlag = psUpd.executeUpdate();

                connection.close();
                ps.close();
                psUpd.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateFlag > 0;
    }

    @Override
    public boolean delete(int id) {
        int deleteFlag;
        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement ps = connection.prepareStatement("Delete from Ticket where ticket_id = ?");
            ps.setInt(1, id);
            deleteFlag = ps.executeUpdate();

            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleteFlag > 0;
    }

    public List<Ticket> readAllWithPlane(int planeId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("Select *  from Ticket  where plane_id = ?");
            ps.setInt(1, planeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tickets.add(TicketMapper.map(rs));
            }

            connection.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
