package sample.spring.chapter09.bankapp.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import sample.spring.chapter09.bankapp.dao.FixedDepositDao;
import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;

@Aspect
@Component
@SuppressWarnings("unused")
public class LoggingAspect {
	private Logger logger = Logger.getLogger(LoggingAspect.class);

	@Pointcut(value = "execution(* sample.spring.chapter09.bankapp.service.*Service.*(..))")
	private void invokeServiceMethods() {

	}

	@Before(value = "invokeServiceMethods()")
	public void log(JoinPoint joinPoint) {
		logger.info("Entering "
				+ joinPoint.getTarget().getClass().getSimpleName() + "'s "
				+ joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.info("args[" + i + "] -->" + args[i]);
		}
	}
}
