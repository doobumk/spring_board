package common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by User on 2017-05-16.
 */

/*
<aop:config>
        <aop:aspect ref="checkLoginState">
            <aop:pointcut id="loginCheck" expression="execution(* document..*.*(..))"/>
            <aop:before pointcut-ref="loginCheck" method="checkState"/>
        </aop:aspect>
    </aop:config>
 */

@Aspect
public class CheckLoginState {
    @Before("execution(* document..*.*(..))")
    public void checkState(){
        System.out.println("[비즈니스 메소드 수행전 동작]");
    }
}

