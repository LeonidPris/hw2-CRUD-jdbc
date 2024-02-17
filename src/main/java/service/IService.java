package service;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Интервейс опреджеляет методы для сервисов
 * */
public interface IService<T> {

    /**
     * Метод должен передавать репозиторию сущность для сохранения в БД,
     * при успехе возвращать true.
     * */
    void createNew(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Метод должен запрашивать у репозитория сущность по id
     * @param id - id элемента таблицы базы данных
     * */
    String findById(int id) throws JsonProcessingException;

    /**
     * Метод должен передавать сущность репозиторию для обновления в БД
     * */
    void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Метод должен передать репозиторию id сущности для удаления из БД
     * */
    void delete (HttpServletRequest request, HttpServletResponse response);

}
