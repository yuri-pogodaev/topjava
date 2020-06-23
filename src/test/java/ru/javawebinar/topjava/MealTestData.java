package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_1_ID = START_SEQ + 2;
    public static final Meal USER_MEAL_1 = new Meal(USER_MEAL_1_ID, of(2020, Month.JUNE, 21, 9, 5), "Завтрак", 500);
    public static final Meal USER_MEAL_2 = new Meal(USER_MEAL_1_ID + 1, of(2020, Month.JUNE, 21, 13, 10), "Обед", 300);
    public static final Meal USER_MEAL_3 = new Meal(USER_MEAL_1_ID + 2, of(2020, Month.JUNE, 21, 19, 22), "Ужин", 1400);
    public static final Meal USER_MEAL_4 = new Meal(USER_MEAL_1_ID + 3, of(2020, Month.JUNE, 20, 9, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_5 = new Meal(USER_MEAL_1_ID + 4, of(2020, Month.JUNE, 20, 14, 11), "Обед", 900);
    public static final Meal USER_MEAL_6 = new Meal(USER_MEAL_1_ID + 5, of(2020, Month.JUNE, 20, 20, 4), "Ужин", 500);
    public static final Meal USER_MEAL_7 = new Meal(USER_MEAL_1_ID + 6, of(2020, Month.JUNE, 22, 8, 0), "Завтрак", 700);

    public static final int USER_UPDATED_MEAL_ID = USER_MEAL_1_ID;

    public static final int ADMIN_MEAL_1_ID = START_SEQ + 9;
    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_1_ID, of(2020, Month.JUNE, 21, 9, 4), "Завтрак", 500);
    public static final Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_1_ID + 1, of(2020, Month.JUNE, 21, 13, 11), "Обед", 700);
    public static final Meal ADMIN_MEAL_3 = new Meal(ADMIN_MEAL_1_ID + 2, of(2020, Month.JUNE, 21, 19, 44), "Ужин", 790);
    public static final Meal ADMIN_MEAL_4 = new Meal(ADMIN_MEAL_1_ID + 3, of(2020, Month.JUNE, 22, 8, 0), "Завтрак", 400);
    public static final Meal ADMIN_MEAL_5 = new Meal(ADMIN_MEAL_1_ID + 4, of(2020, Month.JUNE, 22, 13, 0), "Обед", 1000);
    public static final Meal ADMIN_MEAL_6 = new Meal(ADMIN_MEAL_1_ID + 5, of(2020, Month.JUNE, 22, 19, 0), "Ужин", 800);
    public static final Meal ADMIN_MEAL_7 = new Meal(ADMIN_MEAL_1_ID + 6, of(2020, Month.JUNE, 22, 0, 0), "Ночной дожор", 600);

    public static final int ADMIN_UPDATED_MEAL_ID = ADMIN_MEAL_1_ID;

    public static final LocalDate START_DATE_USER = LocalDate.of(2020, 6, 21);
    public static final LocalDate END_DATE_USER = LocalDate.of(2020, 6, 21);
    public static final LocalDate START_DATE_ADMIN = LocalDate.of(2020, 6, 22);
    public static final LocalDate END_DATE_ADMIN = LocalDate.of(2020, 6, 22);
    public final static List<Meal> ALL_USER_MEALS = Arrays.asList(
            USER_MEAL_7,
            USER_MEAL_3,
            USER_MEAL_2,
            USER_MEAL_1,
            USER_MEAL_6,
            USER_MEAL_5,
            USER_MEAL_4
    );

    public final static List<Meal> ALL_ADMIN_MEALS = Arrays.asList(
            ADMIN_MEAL_6,
            ADMIN_MEAL_5,
            ADMIN_MEAL_4,
            ADMIN_MEAL_7,
            ADMIN_MEAL_3,
            ADMIN_MEAL_2,
            ADMIN_MEAL_1
    );

    public final static List<Meal> FILTERED_ADMIN_MEALS = Arrays.asList(
            ADMIN_MEAL_6,
            ADMIN_MEAL_5,
            ADMIN_MEAL_4,
            ADMIN_MEAL_7
    );
    public final static List<Meal> FILTERED_USER_MEALS = Arrays.asList(
            USER_MEAL_3,
            USER_MEAL_2,
            USER_MEAL_1
    );

    public static Meal getCreatedForUser() {
        return new Meal(null, of(2020, Month.JUNE, 23, 10, 0), "Завтрак USER", 100);
    }

    public static Meal getUpdatedMealUser() {
        return new Meal(USER_MEAL_1_ID, of(2020, Month.JUNE, 21, 10, 50), "Updated", 900);
    }

    public static Meal getCreatedForAdmin() {
        return new Meal(null, of(2020, Month.JUNE, 24, 9, 0), "Завтрак ADMIN", 700);
    }

    public static Meal getUpdatedMealAdmin() {
        return new Meal(ADMIN_MEAL_1_ID, of(2020, Month.JUNE, 21, 10, 44), "Updated", 500);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}