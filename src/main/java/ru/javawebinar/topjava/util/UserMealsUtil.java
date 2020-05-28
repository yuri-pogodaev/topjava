package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        Map<LocalDate, Integer> dayCaloriesMap = new HashMap<>();
        List<UserMealWithExcess> result = new ArrayList<>();
        for (UserMeal meal : meals) {
            LocalDate localDate = meal.getDateTime().toLocalDate();
            int caloriesSum = dayCaloriesMap.getOrDefault(localDate, 0) + meal.getCalories();
            dayCaloriesMap.put(localDate, caloriesSum);
        }


        for (UserMeal meal : meals) {
            LocalTime lt = meal.getDateTime().toLocalTime();
            LocalDate localDate = meal.getDateTime().toLocalDate();
            if (TimeUtil.isBetweenHalfOpen(lt, startTime, endTime)) {
                boolean excess = dayCaloriesMap.get(localDate) > caloriesPerDay;
                result.add(new UserMealWithExcess(meal, excess));
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<LocalDate, Integer> dayCaloriesMap = new HashMap<>();
        meals.forEach(m -> {
            LocalDate ld = m.getDateTime().toLocalDate();
            dayCaloriesMap.merge(ld, m.getCalories(), Integer::sum);
        });

        List<UserMealWithExcess> result = new ArrayList<>();
        meals.stream()
                .filter(m -> TimeUtil.isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(m -> {
                    LocalDate ld = m.getDateTime().toLocalDate();
                    boolean excess = dayCaloriesMap.getOrDefault(ld, 0) > caloriesPerDay;
                    result.add(new UserMealWithExcess(m, excess));
                });
        return result;
    }
}
