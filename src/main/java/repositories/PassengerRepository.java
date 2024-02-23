package repositories;

import connect.ConnectionDB;
import entity.Passenger;
import entity.blprnts.Person;
import mappers.PassengerMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerRepository implements Repository<Person> {

    @Override
    public boolean create(Person entity) {
        int saveFlag;
        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement ps = connection.prepareStatement("Insert Into Person(name, passport) VALUES (?,?)");
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPassport());
            saveFlag = ps.executeUpdate();

            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saveFlag > 0;
    }

    @Override
    public Passenger findById(int id) {
        Passenger person = null;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("Select * from Person  where person_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                person = PassengerMapper.map(rs);
            }

            connection.close(); // это не обязятельн,  try-with-resources же
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean update(Person entity) {
        int updateFlag;
        try(Connection connection = ConnectionDB.getConnection()){
            Passenger person = null;
            PreparedStatement ps = connection.prepareStatement("Select *  from Person where person_id = ?"); //если делать через read() connection закрыт
            ps.setInt(1, entity.getId());                                                    // мб как-то переписать connection?
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                person = PassengerMapper.map(rs);
            }

            if (person == null){
                updateFlag = 0;
            } else {
                String sql = "UPDATE Person SET name = ?, passport = ? where person_id = ?"; // в константу
                PreparedStatement psUpd = checkFields(entity, person, connection, sql);
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
    public boolean deleteById(int id) {
        int deleteFlag;
        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement ps = connection.prepareStatement("Delete from Person where person_id = ?");
            ps.setInt(1, id);
            deleteFlag = ps.executeUpdate();

            connection.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleteFlag > 0;
    }

    private PreparedStatement checkFields(Person newPerson, Person personForUpd, Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (newPerson.getName() != null) {
            preparedStatement.setString(1, newPerson.getName());
        } else {
            preparedStatement.setString(1, personForUpd.getName());
        }
        if (newPerson.getPassport() != 0){
            preparedStatement.setInt(2, newPerson.getPassport());
        } else {
            preparedStatement.setInt(2, personForUpd.getPassport());
        }
        preparedStatement.setInt(3, personForUpd.getId());
        return preparedStatement;
    }
}
