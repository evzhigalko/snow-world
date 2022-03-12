package by.zhigalko.snow.world.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log4j2
@WebListener
public class ApplicationContextInitializeListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) throws RuntimeException {
        try {
            log.info("ApplicationInitializerListener initializes context");
            Class<?> contextClass = Class.forName(sce.getServletContext().getInitParameter("context"));
            ApplicationContext context = new AnnotationConfigApplicationContext(contextClass);
            sce.getServletContext().setAttribute("context", context);
        } catch (Exception e) {
                log.info(e.getMessage());
                throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ApplicationInitializerListener closes context");
        AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext) sce.getServletContext().getAttribute("context");
        context.close();
    }
}
