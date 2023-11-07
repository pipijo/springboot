package com.yunhe.aop;

import com.yunhe.entity.SysLog;
import com.yunhe.mapper.LogMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 无意
 * @description 日志切面类，用于记录方法的执行日志
 * @create 2023/11/7/ 8:16
 */
@Aspect
@Component
public class Advice {

    @Autowired
    private LogMapper logMapper;

    @Around(value = "myPointcut()")
    public Object syslog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取security上下文环境
        SecurityContext context = SecurityContextHolder.getContext();
        // 获取认证信息
        Authentication authentication = context.getAuthentication();
        // 判断是否登录
        if (authentication != null) {
            // 用户已登录
            // 获取该类的字节码文件
            Logger logger = LoggerFactory.getLogger(Advice.class);
            // 获取当前时间
            Date date = new Date();
            // 获取执行的目标类
            Class<?> aClass = joinPoint.getTarget().getClass();
            // 获取正在执行的方法
            String name = joinPoint.getSignature().getName();
            // 如果是以 "find" 开头的方法，不进行日志记录
            if (name.startsWith("find")) {
                return joinPoint.proceed();
            }
            // 获取正在执行的方法参数
            Object[] args = joinPoint.getArgs();
            logger.info("方法执行时间：" + date.toLocaleString() + "  执行的目标类：" + aClass + "  执行的方法：" + name + "  方法执行参数：" + Arrays.toString(args));
            // 执行目标方法
            Object proceed = joinPoint.proceed();
            // 创建SysLog日志对象
            SysLog sysLog = new SysLog();
            // 添加到数据库的方法
            String methodName = "[类名]  " + aClass + "  [方法名] " + name;
            sysLog.setMethod(methodName);
            // 获取用户名
            User user = (User) authentication.getPrincipal();
            sysLog.setUsername(user.getUsername());
            // 执行方法时间
            sysLog.setVisitTime(new Date(date.getTime()));
            // 获取请求对象
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            // 获取请求路径
            sysLog.setUrl(request.getRequestURI().toString());
            // 获取请求IP
            sysLog.setIp(request.getRemoteAddr());
            // 获取当前系统的毫秒值
            long currentTimeMillis = System.currentTimeMillis();
            sysLog.setExecutionTime(currentTimeMillis - date.getTime());
            // 添加到数据库
            logMapper.addLog(sysLog);
            return proceed;
        } else {
            // 用户未登录，直接执行目标方法
            return joinPoint.proceed();
        }
    }

    /**
     * 定义切点，给所有方法进行日志增强
     */
    @Pointcut(value = "execution( * com.yunhe.service.impl.*.*(..))")
    public void myPointcut() {}
}
