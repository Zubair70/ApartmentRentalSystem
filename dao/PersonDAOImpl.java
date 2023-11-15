package dao;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements HandlerDAO<Person> {
    private static List<Person> persons = new ArrayList<>();


    @Override
    public boolean save(Person obj) {
        return false;
    }

    @Override
    public Person getByIndex(int index) {
        return null;
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public boolean updateByIndex(int index, Person obj) {
        return false;
    }

    @Override
    public boolean updateById(int id, Person obj) {
        return false;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
