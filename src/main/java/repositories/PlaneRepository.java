package repositories;

import connect.ConnectionDB;
import entity.Plane;
import mappers.PlaneMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaneRepository implements Repository<Plane> {

    @Override
    public boolean create(Plane entity) {
        int saveFlag;
        try(Connection connection = ConnectionDB.getConnection()){
            String sql = "Insert Into Plane(flight) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getFlight());
            saveFlag = ps.executeUpdate();

            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saveFlag > 0;
    }

    @Override
    public Plane read(int id) {
        Plane plane = null;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("Select *  from Plane where plane_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                plane = PlaneMapper.map(rs);
            }

            connection.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plane;
    }

    @Override
    public boolean update(Plane entity) {
        int updateFlag;
        try(Connection connection = ConnectionDB.getConnection()){
            Plane planeFromDB = null;
            PreparedStatement ps = connection.prepareStatement("Select *  from Plane where plane_id = ?");
            ps.setInt(1, entity.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                planeFromDB = PlaneMapper.map(rs);
            }

            if (planeFromDB == null){
                updateFlag = 0;
            } else {
                PreparedStatement psUpd = connection.prepareStatement("UPDATE Plane SET flight = ? where plane_id = ?");
                psUpd.setString(1, entity.getFlight());
                psUpd.setInt(2, planeFromDB.getId());
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
            PreparedStatement ps = connection.prepareStatement("Delete from Plane where plane_id = ?");
            ps.setInt(1, id);
            deleteFlag = ps.executeUpdate();

            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleteFlag > 0;
    }
}
