package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;
    @Autowired
    private MealRepository repository;

    @Test
    public void get() {
        assertMatch(service.get(USER_MEAL_1_ID, USER_ID), USER_MEAL_1);
        assertMatch(service.get(ADMIN_MEAL_1_ID, ADMIN_ID), ADMIN_MEAL_1);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_1_ID, USER_ID);
        Assert.assertNull(repository.get(USER_MEAL_1_ID, USER_ID));
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);
        Assert.assertNull(repository.get(ADMIN_MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        final List<Meal> filteredMealsUser = service.getBetweenInclusive(START_DATE_USER, END_DATE_USER, USER_ID);
        assertMatch(filteredMealsUser, FILTERED_USER_MEALS);
        final List<Meal> filteredMealsAdmin = service.getBetweenInclusive(START_DATE_ADMIN, END_DATE_ADMIN, ADMIN_ID);
        assertMatch(filteredMealsAdmin, FILTERED_ADMIN_MEALS);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, ALL_USER_MEALS);
        meals = service.getAll(ADMIN_ID);
        assertMatch(meals, ALL_ADMIN_MEALS);
    }

    @Test
    public void update() {
        Meal update = getUpdatedMealUser();
        service.update(update, USER_ID);
        assertMatch(service.get(USER_UPDATED_MEAL_ID, USER_ID), update);

        update = getUpdatedMealAdmin();
        service.update(update, ADMIN_ID);
        assertMatch(service.get(ADMIN_UPDATED_MEAL_ID, ADMIN_ID), update);
    }

    @Test
    public void create() {
        Meal newMeal = getCreatedForUser();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(created.getId());
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);

        newMeal = getCreatedForAdmin();
        created = service.create(newMeal, ADMIN_ID);
        Integer newIdAdmin = created.getId();
        newMeal.setId(created.getId());
        assertMatch(created, newMeal);
        assertMatch(service.get(newIdAdmin, ADMIN_ID), newMeal);
    }

    @Test
    public void deleteAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL_1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void updateAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdatedMealUser(), ADMIN_ID));
    }
}