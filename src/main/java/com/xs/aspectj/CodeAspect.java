package com.xs.aspectj;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import com.xs.domain.TokenModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaosong on 2017/3/11.
 */
//@Aspect//定义切面
//@Component
public class CodeAspect {
    private static final Logger logger = LoggerFactory.getLogger(CodeAspect.class);

    //定义切点
    @Pointcut("execution(* com.xs.web.*Controller.*(..))")
    public void excudeController(){}

    @Around("excudeController()")
    public Object doControllerAround (ProceedingJoinPoint pjp)throws Throwable{
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
//        DeferredResult<String> deferredResult =( DeferredResult<String>)result;
//        String resultFromCallback = (String) deferredResult.getResult();
//        System.out.println("正常返回的："+resultFromCallback);
//        deferredResult.setResult("aop之后的返回"+resultFromCallback);
        TokenModel deferredResult=( TokenModel)result;
        deferredResult.setUserId( deferredResult.getUserId()+1);
        return deferredResult;

    }

//    //定义切点
//    @Pointcut("execution(* com.xs.service.LongTermTaskCallback(..))")
//    public void excudeService(){}
//
//    @Around("excudeService()")
//    public Object doServiceAround (ProceedingJoinPoint pjp)throws Throwable{
//        return null;
//
//    }

}
