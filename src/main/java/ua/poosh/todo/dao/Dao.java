package ua.poosh.todo.dao;

import java.util.List;


public interface Dao<T,ID> {


    T save(T t);

    T findOne(ID id);

    List<T> findAll();

    T delete(ID id);

    T update(T t);


}
