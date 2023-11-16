package dao;

public interface HandlerDAO<T> {

    /**
     * Retrieves the size of the list
     * @return size of the list
     */
    public int size();

    /**
     * Adds a new object in the list
     *
     * @param obj object to be added in the list
     * @return true, if the list updates
     */
    public boolean save(T obj);

    /**
     * Finds and returns object from the list by index
     *
     * @param index index of the object to find
     * @return object, which matches the index
     */
    public T getByIndex(int index);

    /**
     * Finds and returns object from the list by its identifier
     *
     * @param id identifier of the object to find
     * @return object, which matches the id
     */
    public T getById(int id);

    /**
     * Updates the object in the list by its index
     *
     * @param index index of the object to be updated in the list
     * @param obj   new object to be added
     * @return true, if the list updates
     */
    public boolean updateByIndex(int index, T obj);

    /**
     * Updates the object in the list by its identifier
     *
     * @param id  identifier of the object to be updated in the list
     * @param obj new object to be added
     * @return true, if the list updates
     */
    public boolean updateById(int id, T obj);

    /**
     * Removes object from the list by its index
     *
     * @param index index of the object to be removed
     * @return @return true, if the list updates
     */
    public boolean deleteByIndex(int index);

    /**
     * Removes object from the list by its identifier
     *
     * @param id identifier of the object to be removed
     * @return @return true, if the list updates
     */
    public boolean deleteById(int id);
}
