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

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app-jdbs.xml",
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
        Meal meal = service.get(USER_MEAL_1_ID, USER_ID);
        assertThat(meal).isEqualToComparingFieldByField(USER_MEAL_1);
        meal = service.get(ADMIN_MEAL_1_ID, ADMIN_ID);
        assertThat(meal).isEqualToComparingFieldByField(ADMIN_MEAL_1);
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
        final List<Meal> filteredMealsUser = service.getBetweenInclusive(LocalDate.of(2020, Month.JUNE, 21), LocalDate.of(2020, Month.JUNE, 21), USER_ID);
        final List<Meal> FILTERED_USER_MEALS = Arrays.asList(
                USER_MEAL_3,
                USER_MEAL_2,
                USER_MEAL_1
        );
        Assert.assertEquals(FILTERED_USER_MEALS, filteredMealsUser);
        final List<Meal> filteredMealsAdmin = service.getBetweenInclusive(LocalDate.of(2020, Month.JUNE, 22), LocalDate.of(2020, Month.JUNE, 22), ADMIN_ID);
        final List<Meal> FILTERED_ADMIN_MEALS = Arrays.asList(
                ADMIN_MEAL_6,
                ADMIN_MEAL_5,
                ADMIN_MEAL_4,
                ADMIN_MEAL_7
        );
        Assert.assertEquals(FILTERED_ADMIN_MEALS, filteredMealsAdmin);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        final List<Meal> ALL_USER_MEALS = Arrays.asList(
                USER_MEAL_7,
                USER_MEAL_3,
                USER_MEAL_2,
                USER_MEAL_1,
                USER_MEAL_6,
                USER_MEAL_5,
                USER_MEAL_4
        );
        Assert.assertEquals(ALL_USER_MEALS, meals);
        meals = service.getAll(ADMIN_ID);
        final List<Meal> ALL_ADMIN_MEALS = Arrays.asList(
                ADMIN_MEAL_6,
                ADMIN_MEAL_5,
                ADMIN_MEAL_4,
                ADMIN_MEAL_7,
                ADMIN_MEAL_3,
                ADMIN_MEAL_2,
                ADMIN_MEAL_1
        );
        Assert.assertEquals(ALL_ADMIN_MEALS, meals);
    }

    @Test
    public void update() {
        service.update(USER_UPDATED_MEAL, USER_ID);
        assertThat(service.get(USER_UPDATED_MEAL_ID, USER_ID)).isEqualToComparingFieldByField(USER_UPDATED_MEAL);
        service.update(ADMIN_UPDATED_MEAL, ADMIN_ID);
        assertThat(service.get(ADMIN_UPDATED_MEAL_ID, ADMIN_ID)).isEqualToComparingFieldByField(ADMIN_UPDATED_MEAL);
    }

    @Test
    public void create() {
        Meal created = service.create(USER_NEW_MEAL, USER_ID);
        USER_NEW_MEAL.setId(created.getId());
        assertThat(created).isEqualToComparingFieldByField(USER_NEW_MEAL);
        assertThat(service.get(created.getId(), USER_ID)).isEqualToComparingFieldByField(USER_NEW_MEAL);
        created = service.create(ADMIN_NEW_MEAL, ADMIN_ID);
        ADMIN_NEW_MEAL.setId(created.getId());
        assertThat(created).isEqualToComparingFieldByField(ADMIN_NEW_MEAL);
        assertThat(service.get(created.getId(), ADMIN_ID)).isEqualToComparingFieldByField(ADMIN_NEW_MEAL);
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
        assertThrows(NotFoundException.class, () -> service.update(USER_UPDATED_MEAL, ADMIN_ID));
    }
}