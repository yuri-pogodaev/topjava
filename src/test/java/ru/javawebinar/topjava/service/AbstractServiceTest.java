package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractServiceTest {
    private static final Logger log = LoggerFactory.getLogger(Class.class);
    private static Map<String, Long> map = new HashMap<>();
    private static long startLog;
    private static long endLog;
    private static String out;

    @Rule
    public TestRule nameAndTime = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            startLog = System.nanoTime();

//            super.starting(description);
        }

        @Override
        protected void finished(Description description) {
            endLog = -(startLog - System.nanoTime());
            endLog = TimeUnit.MILLISECONDS.convert(endLog, TimeUnit.NANOSECONDS);
//            super.finished(description);
            out = "Test " + description.getMethodName() + " took " + endLog + " ms";
            log.info(out);
            map.put(description.getMethodName(), endLog);
        }
    };

    @AfterClass
    public static void finish() {
        String format = ("%25s%12d ms \n");
        StringBuilder sb = new StringBuilder().append("\n");
        map.forEach((key, value) -> {
            sb.append(String.format(format,key,value));
        });
        log.info(sb.toString());
    }
}
