package cn.navg.laboratory.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;


@Component
@Aspect
public class SystemLogAspect {
    //系统登录日志
    @Pointcut("execution(* cn.navg.laboratory.controller.admin.UserManagementController.login(..))")
    public void loginPointcut() {}

    //系统登录日志 记录
    
}
