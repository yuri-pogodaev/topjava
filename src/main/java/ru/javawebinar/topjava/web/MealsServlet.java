package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MemoryStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;


public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new MemoryStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        storage.create(meal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String operation = request.getParameter("operation");
        Meal meal;
        switch (operation == null ? "allMeals" : operation) {
            case "delete":
                String id = request.getParameter("id");
                log.debug("delete {}", id);
                storage.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                if (Objects.equals(operation, "create")) {
                    meal = new Meal(null, "", 0);
                    log.debug("create new");
                } else {
                    meal = storage.getMeal(Integer.parseInt(request.getParameter("id")));
                    log.debug("{}", "update ".concat(meal.getId().toString()));
                }
                log.debug("{}", Objects.requireNonNull(meal).isNotExist() ? "create new" : "update ".concat(meal.getId().toString()));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/addMeals.jsp").forward(request, response);
                break;
            case "allMeals":
            default:
                log.debug("redirect to meals");
                request.setAttribute("meals",
                        MealsUtil.nonFilteredByStreams(new ArrayList<>(storage.readAll()), 2000));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }
}