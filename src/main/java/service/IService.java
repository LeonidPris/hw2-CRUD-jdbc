package service;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Интервейс определяет методы для сервисов
 * */
public interface IService { // по факту получился не сервисный слой,
                            // а обработчик данных, который должен работать с сервисом вместе

    /**
     * Метод должен парсить и передавать репозиторию сущность из тела запроса для сохранения в БД,
     * возвращать в response код результата.
     * */
    void createNew(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Метод должен запрашивать у репозитория сущность по id и возвращать JSON
     * @param id - id элемента таблицы базы данных
     * */
    String findById(int id) throws JsonProcessingException;

    /**
     * Метод должен подготавливать и передавать сущность репозиторию для обновления в БД
     * */
    void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Метод должен передать репозиторию id сущности для удаления из БД
     * */
    void delete (HttpServletRequest request, HttpServletResponse response);

}
