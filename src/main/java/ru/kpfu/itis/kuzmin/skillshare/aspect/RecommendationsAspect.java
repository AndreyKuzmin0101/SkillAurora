package ru.kpfu.itis.kuzmin.skillshare.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class RecommendationsAspect {

    private Map<Long, Integer> readings;
    private Date date;

    private RecommendationsAspect() {
        this.readings = new HashMap<>();
        date = new Date(System.currentTimeMillis());
    }

    @Pointcut("execution(public * ru.kpfu.itis.kuzmin.skillshare.controller.ArticleController.getArticleById(..))")
    public void read(){}


    @AfterReturning("read()")
    public void countReadingsPerDay(JoinPoint joinPoint){
        Date requestDate = new Date(System.currentTimeMillis());
        if (requestDate.getDay() != date.getDay()) {
            readings = new HashMap<>();
            date = requestDate;
        }

        Long id = (Long) joinPoint.getArgs()[0];

        Integer countReadings = readings.get(id);
        if (countReadings == null) {
            readings.put(id, 1);
        } else {
            readings.put(id, countReadings + 1);
        }
    }

    public Long getMostPopularArticle() {
        Long result = null;
        for (Long id: readings.keySet()) {
            if (result == null) {
                result = id;
                continue;
            }

            if (readings.get(id).compareTo(readings.get(result)) > 0) {
                result = id;
            }
        }
        return result;
    }
}