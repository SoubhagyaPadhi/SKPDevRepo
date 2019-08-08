package com.skp.casntemplt.demo.aspect.impl;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
	
	@Around("execution(* com.skp.casntemplt.demo.controller.* .*(..))")
	public Object logBeforeControllerApi(ProceedingJoinPoint jointPoint) throws Throwable{
		LOGGER.info("		*****--------------------*****");
		LOGGER.info("		Logging Before For Controller API:-"+jointPoint.getSignature().getName());
		LOGGER.info("		Loggging Arguements:-"+Arrays.toString(jointPoint.getArgs()));
		Object result = jointPoint.proceed();
		LOGGER.info("		Logging After For Controller API:-"+jointPoint.getSignature().getName());
		LOGGER.info("		*****--------------------*****");
		return result;
	}
	
	//@Around("execution(* com.skp.casntemplt.demo.service.impl.* .*(..))")
	@Around("execution(* com.skp.casntemplt.demo.service.impl.EmployeeServiceImpl.*(..))")
	public Object logBeforeServiceApi(ProceedingJoinPoint jointPoint) throws Throwable{
		LOGGER.info("       *****--------------------*****");
		LOGGER.info("		Logging Before For Service API:-"+jointPoint.getSignature().getName());
		LOGGER.info("		Loggging Arguements:-"+Arrays.toString(jointPoint.getArgs()));
		Object result = jointPoint.proceed();
		LOGGER.info("		Logging After For Service API:-"+jointPoint.getSignature().getName());
		LOGGER.info("       *****--------------------*****");
		return result;
	}
	
	@Around("@annotation(com.skp.casntemplt.demo.utils.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTImeMilliSecs = System.currentTimeMillis();
		Object result = joinPoint.proceed();
	    long endTimeMilliSecs = System.currentTimeMillis();
	    LOGGER.info("       Total Execution Time For API(measure:-"+TimeUnit.MILLISECONDS.toSeconds(endTimeMilliSecs-startTImeMilliSecs)+"secs)");
	    return result;
	}

}
