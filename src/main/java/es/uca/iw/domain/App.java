package es.uca.iw.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = 
             new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
    	 
    	MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("joseantoniomfking@gmail.com",
    		   "joseantonio_conil@hotmail.com",
    		   "Testing123", 
    		   "Testing only \n\n Hello Spring Email Sender");
        
    }
}