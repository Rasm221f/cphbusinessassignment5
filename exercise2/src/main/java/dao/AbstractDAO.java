package dao;

import util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T> implements IDAO<T> {

    protected Connection getConnection() throws Exception {
        return DatabaseConnection.getConnection();
    }

    @Override
    public abstract List<T> getAll() throws Exception;

    @Override
    public abstract T getById(int id) throws Exception;

    @Override
    public abstract T create(T t) throws Exception;

    @Override
    public abstract T update(T t) throws Exception;

    @Override
    public abstract void delete(int id) throws Exception;
}
