package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    int deleteMealByIdAndUserId(int id, int userId);

    Meal getMealByIdAndUserId(int id, int userId);

    List<Meal> getAllByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> getAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThanOrderByDateTimeDesc
            (int userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.user WHERE m.id=:id AND m.user.id=:userId")
    Meal getWithUser(@Param("id") int id, @Param("userId") int userId);
}
