package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStorage implements Storage {
    private final Map<Integer, Meal> mealsHashMap = new ConcurrentHashMap<>();
    private AtomicInteger currentID = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public void save(Meal meal) {
        if (Objects.nonNull(meal)) {
            if (meal.isNotExist() || meal.getId() > currentID.get() || meal.getId() <= 0) {
                meal.setId(currentID.incrementAndGet());
            }
            mealsHashMap.put(meal.getId(), meal);
        }
    }

    @Override
    public Meal getMeal(int id) {
        return mealsHashMap.get(id);
    }

    @Override
    public void delete(int id) {
        mealsHashMap.remove(id);
    }

    @Override
    public Collection<Meal> readAll() {
        return mealsHashMap.values();
    }
}