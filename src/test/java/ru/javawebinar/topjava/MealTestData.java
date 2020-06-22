package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_1_ID = START_SEQ + 2;
    public static final Meal USER_MEAL_1 = new Meal(USER_MEAL_1_ID, LocalDateTime.of(2020, Month.JUNE, 21, 9, 5), "Завтрак", 500);
    public static final Meal USER_MEAL_2 = new Meal(USER_MEAL_1_ID + 1, LocalDateTime.of(2020, Month.JUNE, 21, 13, 10), "Обед", 300);
    public static final Meal USER_MEAL_3 = new Meal(USER_MEAL_1_ID + 2, LocalDateTime.of(2020, Month.JUNE, 21, 19, 22), "Ужин", 1400);
    public static final Meal USER_MEAL_4 = new Meal(USER_MEAL_1_ID + 3, LocalDateTime.of(2020, Month.JUNE, 20, 9, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_5 = new Meal(USER_MEAL_1_ID + 4, LocalDateTime.of(2020, Month.JUNE, 20, 14, 11), "Обед", 900);
    public static final Meal USER_MEAL_6 = new Meal(USER_MEAL_1_ID + 5, LocalDateTime.of(2020, Month.JUNE, 20, 20, 4), "Ужин", 500);
    public static final Meal USER_MEAL_7 = new Meal(USER_MEAL_1_ID + 6, LocalDateTime.of(2020, Month.JUNE, 22, 8, 0), "Завтрак", 700);

    public static final int USER_UPDATED_MEAL_ID = USER_MEAL_1_ID;
    public static final Meal USER_UPDATED_MEAL = new Meal(USER_UPDATED_MEAL_ID, LocalDateTime.of(2021, Month.JUNE, 21, 10, 50), "Updated", 900);
    public static final Meal USER_NEW_MEAL = new Meal(null, LocalDateTime.of(2020, Month.JUNE, 23, 10, 0), "Завтрак USER", 100);

    public static final int ADMIN_MEAL_1_ID = START_SEQ + 9;
    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_1_ID, LocalDateTime.of(2020, Month.JUNE, 21, 9, 4), "Завтрак", 500);
    public static final Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_1_ID + 1, LocalDateTime.of(2020, Month.JUNE, 21, 13, 11), "Обед", 700);
    public static final Meal ADMIN_MEAL_3 = new Meal(ADMIN_MEAL_1_ID + 2, LocalDateTime.of(2020, Month.JUNE, 21, 19, 44), "Ужин", 790);
    public static final Meal ADMIN_MEAL_4 = new Meal(ADMIN_MEAL_1_ID + 3, LocalDateTime.of(2020, Month.JUNE, 22, 8, 0), "Завтрак", 400);
    public static final Meal ADMIN_MEAL_5 = new Meal(ADMIN_MEAL_1_ID + 4, LocalDateTime.of(2020, Month.JUNE, 22, 13, 0), "Обед", 1000);
    public static final Meal ADMIN_MEAL_6 = new Meal(ADMIN_MEAL_1_ID + 5, LocalDateTime.of(2020, Month.JUNE, 22, 19, 0), "Ужин", 800);
    public static final Meal ADMIN_MEAL_7 = new Meal(ADMIN_MEAL_1_ID + 6, LocalDateTime.of(2020, Month.JUNE, 22, 0, 0), "Ночной дожор", 600);

    public static final int ADMIN_UPDATED_MEAL_ID = ADMIN_MEAL_1_ID;
    public static final Meal ADMIN_UPDATED_MEAL = new Meal(ADMIN_MEAL_1_ID, LocalDateTime.of(2020, Month.JUNE, 21, 10, 44), "Updated", 500);
    public static final Meal ADMIN_NEW_MEAL = new Meal(null, LocalDateTime.of(2020, Month.JUNE, 24, 9, 0), "Завтрак ADMIN", 700);
}