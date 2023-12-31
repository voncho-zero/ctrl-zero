package com.onezero.permission;

import com.onezero.security.SecurityUtil;
import com.onezero.util.Context;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Aspect
@Configuration
public class DataPermissionAspect {

    @Before("@annotation(com.onezero.permission.DataPermission)")
    public void before(JoinPoint joinPoint) {
//        joinPoint.getSignature()
        Long userId = SecurityUtil.getUserId();
        if (Objects.nonNull(userId)) {
            //todo list permission
            Context.put("data-permission", new com.onezero.domain.system.DataPermission());
        }
    }

    @Around("@annotation(com.onezero.permission.DataPermission)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }
}
