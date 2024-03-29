package repositories;
/**
 * Интерфейс определяет методы для всех CRUD репозиториев
 * */
public interface Repository<T> {

    /**
     * Метод должен создавать в базе данных новую сущность, при успехе возвращать true
     * @param entity элемент таблицы базы данных
     * */
    boolean create(T entity);

    /**
     * Метод должен возвращать из базы данных сущность по id
     * @param id - id элемента таблицы базы данных
     * */
    T findById(int id);

    /**
     * Метод должен обновить состояние переданной сущности в базе данных
     * @param entity объект-сущность
     * */
    boolean update(T entity);

    /**
     * Метод должен удалить сущность из базы данных по id
     * @param id - id элемента таблицы базы данных
     * */
    boolean deleteById(int id);
}
