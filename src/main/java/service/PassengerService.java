package service;

import entity.Passenger;
import entity.blprnts.Person;
import repositories.PassengerRepository;

public class PassengerService implements IService<Person>{
    private final PassengerRepository personRepository;
    public PassengerService() {
        personRepository = new PassengerRepository();
    }

    @Override
    public boolean createNew(Person passenger){
        return personRepository.create(passenger);
    }

    @Override
    public Passenger findById(int id){
        return personRepository.read(id);
    }

    @Override
    public boolean updateData(Person passenger){ // если DTO без id ничего не найдет -> false
        return personRepository.update(passenger);
    }

    @Override
    public boolean deleteById(int id){
        return personRepository.delete(id);
    }

//    public static void main(String[] args) {
//        var service = new PassengerService();
//        System.out.println(service.findById(1));
//    }

}
