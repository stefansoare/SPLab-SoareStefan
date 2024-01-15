package Classes;

import java.util.List;

public interface Repository<T> {
    public List<T> getAll();
    public T find(String Id);
    void add(T obj);
    void update(String Id, T book);
    void delete(String Id);

    int findIndex(String Id);
}