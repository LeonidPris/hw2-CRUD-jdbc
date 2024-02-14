package repositories;

import connect.ConnectionDB;
import dto.PersonDTO;
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
    public Passenger read(int id) {
        Passenger person = null;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("Select *  from Person  where person_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                person = PassengerMapper.map(rs);
            }

            connection.close();
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
                PreparedStatement psUpd = connection.prepareStatement("UPDATE Person SET name = ?, passport = ? where person_id = ?");
                psUpd.setString(1, entity.getName());
                psUpd.setInt(2, entity.getPassport());
                psUpd.setInt(3, person.getId());
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

//    public static void main(String[] args) {
//        PassengerRepository personRepository = new PassengerRepository();
//        System.out.println(personRepository.read(1));
////        System.out.println(personRepository.create(new PersonDTO("Ivan2", 1188777666)));
////        System.out.println(personRepository.read(4));
////        System.out.println(personRepository.update(new Passenger(4,"Ivan2Change", 1111222222)));
////        personRepository.delete(3);
//    }
}
