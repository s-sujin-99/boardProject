package com.zeus.common.aop;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class ServiceLoggerAdvice {

	/*
	 * //target = com.zeus.service.BoardServiceImpl 
	 * //Joinpoint = 멤버함수(모든 함수 대상 지정)
	 * 
	 * @Before("execution(* com.zeus.service.BoardService*.*(..))") 
	 * public void startLog(JoinPoint jp) { 
	 * log.info("**************Start log***************");
	 * log.info("log - jp.getSignature : " + jp.getSignature());
	 * log.info("log - jp.getArgs : " + Arrays.toString(jp.getArgs()));
	 * log.info("**************************************"); }
	 */
	/*
	 * @AfterReturning(pointcut = "execution(* com.zeus.service.BoardService*.*(..))", returning = "result")
	 * public void logReturning(JoinPoint jp, Object result) {
	 * 
	 * log.info("**************Start log***************"); 
	 * log.info("logReturning");
	 * log.info("logReturning : " + jp.getSignature()); log.info("logReturning : " + result); 
	 * log.info("**************************************");
	 * 
	 * }
	 */

	@AfterThrowing(pointcut = "execution(* com.zeus.service.BoardService*.*(..))", throwing = "e")
	public void logThrowing(JoinPoint jp, Exception e) {

		log.info("**************Start log***************");
		log.info("logThrowing" + new Date().toString());
		log.info("logThrowing : " + jp.getSignature());
		log.info("logThrowing : " + e);
		log.info("**************************************");

	}

	/*
	 * @After("execution(* com.zeus.service.BoardService*.*(..))") public void
	 * endLog(JoinPoint jp) {
	 * 
	 * log.info("**************Start log***************");
	 * log.info("endLog" + new Date().toString());
	 * log.info("endLog : " + jp.getSignature());
	 * log.info("endLog : " +Arrays.toString(jp.getArgs()));
	 * log.info("**************************************");
	 * 
	 * }
	 */
	@Around("execution(* com.zeus.service.BoardService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("**************Start log***************");
		long startTime = System.currentTimeMillis();
		log.info("timeLog : " + Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		log.info("timeLog : " + pjp.getSignature().getName() + " : " + (endTime - startTime));
		
		return result;
	}

}
