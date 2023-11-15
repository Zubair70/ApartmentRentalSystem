package dao;

public interface HandlerDAO<T> {
    public boolean save(T obj);

    public T getByIndex(int index);

    public T getById(int id);

    public boolean updateByIndex(int index, T obj);

    public boolean updateById(int id, T obj);

    public boolean deleteByIndex(int index);

    public boolean deleteById(int id);
}
