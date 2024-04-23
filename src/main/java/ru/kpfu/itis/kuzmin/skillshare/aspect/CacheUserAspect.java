package ru.kpfu.itis.kuzmin.skillshare.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheUserAspect {

    private Map<Long, UserResponseDto> users;

    public CacheUserAspect() {
        users = new HashMap<>();
    }

    @Pointcut("execution(public * ru.kpfu.itis.kuzmin.skillshare.service.UserService.getUserById(..))")
    public void cache(){}

    @Pointcut("execution(public * ru.kpfu.itis.kuzmin.skillshare.service.UserService.update(..))")
    public void updateCache(){}

    @Pointcut("execution(public * ru.kpfu.itis.kuzmin.skillshare.service.UserService.deleteUser(..))")
    public void deleteFromCache(){}

    @Around("cache()")
    public UserResponseDto getFromCacheOrInsertToCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = (Long) joinPoint.getArgs()[0];

        UserResponseDto response= users.get(id);
        if (response == null) {
            response = (UserResponseDto) joinPoint.proceed();
            users.put(id, response);
        }

        return response;
    }

    @AfterReturning("updateCache()")
    public void updateCacheUsers(JoinPoint joinPoint) {
        Long id = (Long) joinPoint.getArgs()[0];
        UserRequestDto userRequestDto = (UserRequestDto) joinPoint.getArgs()[1];
        UserResponseDto userResponseDto = new UserResponseDto(
                userRequestDto.username(),
                userRequestDto.realName(),
                userRequestDto.age(),
                userRequestDto.email(),
                userRequestDto.country(),
                userRequestDto.city(),
                userRequestDto.password(),
                userRequestDto.registerDate(),
                userRequestDto.rating()
        );

        users.put(id, userResponseDto);
    }

    @AfterReturning("deleteFromCache()")
    public void deleteUserFromCache(JoinPoint joinPoint) {
        Long id = (Long) joinPoint.getArgs()[0];
        users.remove(id);
    }
}
