package common;

import com.sun.deploy.net.HttpResponse;
import member.login.AuthInfo;
import member.login.LoginCommand;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2017-05-31.
 */
@Aspect
public class RecordActivityLog   {


    private static AuthInfo savedInfo;




    @Pointcut("execution(* controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void loginLog(){}

    @Pointcut("execution(* member.login.AuthService.authenticate(..))")
    public void getUserInfo(){}


    @Before(value = "loginLog()")
    public void writeFileLog(JoinPoint joinPoint) throws Throwable {
        Date nowDate = new Date();
        File file;
        FileWriter fileWriter;


        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for(Method method : methods){
            if(method.getName().equals(joinPoint.getSignature().getName()) && savedInfo != null){
                String[] values = method.getAnnotation(RequestMapping.class).value();
                for(int i=0; i<values.length;i++){
                        if(savedInfo != null) {
                            Path url = Paths.get("D:/temp/");
                            Path logfile = Paths.get("D:/temp/"+savedInfo.getEmail()+".txt");
                            if(Files.notExists(url)){
                                Files.createDirectories(url);
                            }
                            if(Files.notExists(logfile)){
                                Files.createFile(logfile);
                            }
                            file = new File("D:/temp/"+savedInfo.getEmail()+".txt");
                            fileWriter = new FileWriter(file,true);
                            System.out.println("[writeFileLog]실행");
                            System.out.println(values[i]);
                            fileWriter.write("접속 시간 :"+nowDate+" 접속자 아이디 번호: " + savedInfo.getId() + " 접속자 이메일: "
                                    + savedInfo.getEmail() +" 접속자 이름 : "+savedInfo.getName()+" 동작 : " +values[i] + "\r\n");
                            fileWriter.flush();
                            fileWriter.close();
                        }
                }
            }
        }

    }

    @AfterReturning(value = "getUserInfo())",returning = "authInfo")
    public void info(AuthInfo authInfo){
        savedInfo = authInfo;
    }




}
