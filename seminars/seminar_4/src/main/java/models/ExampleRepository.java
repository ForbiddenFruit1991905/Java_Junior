package models;

import java.sql.SQLException;
import java.util.Collection;

public interface ExampleRepository<T, TId> {
    void add(T item) throws SQLException;
    void update(T item);
    void delete(T item);
    T getById(TId item);
    Collection<T> getAll();
}
