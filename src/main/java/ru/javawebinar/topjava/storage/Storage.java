package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Storage {
    void save(Meal meal);

    Meal getMeal(int id);

    void delete(int id);

    Collection<Meal> readAll();
}
