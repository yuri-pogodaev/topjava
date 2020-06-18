package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService mealService;

    @Autowired
    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public void delete(int id) {
        log.info("delete {}", id);
        mealService.delete(id, SecurityUtil.authUserId());
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        log.info("create {}", meal);
        int userId = SecurityUtil.authUserId();
        log.info("userId {}", userId);
        return mealService.create(meal, userId);
    }

    public void update(Meal meal, int Id) {
        assureIdConsistent(meal, Id);
        log.info("update {}", meal);
        int userId = SecurityUtil.authUserId();
        log.info("userId {}", userId);
        mealService.update(meal, userId);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        int userId = SecurityUtil.authUserId();
        log.info("userId {}", userId);
        return mealService.get(id, userId);
    }

    public List<MealTo> getAllWithTimeFilter(LocalTime start, LocalTime end, LocalDate startDate, LocalDate endTime) {
        log.info("getAllFilter");
        int userId = SecurityUtil.authUserId();
        return MealsUtil.getFilteredTos(mealService.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY, start, end)
                .stream().filter(x -> x.getDateTime().toLocalDate().isBefore(endTime)).filter(x -> x.getDateTime().toLocalDate().isAfter(startDate)).collect(Collectors.toList());
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getWithFilter(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Meal> mealsDateFiltered = mealService.getBetweenDates(
                startDate != null ? startDate : LocalDate.of(1, 1, 1),
                endDate != null ? endDate : LocalDate.of(4000, 1, 1), userId);

        return MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX
        );
    }
}