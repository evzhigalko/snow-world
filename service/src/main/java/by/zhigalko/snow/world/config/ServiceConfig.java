package by.zhigalko.snow.world.config;

import by.zhigalko.snow.world.aspect.ServiceAspect;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Objects;
import java.util.Properties;

@Log4j2
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceConfig {
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean
    public ServiceAspect serviceAspect() {
        log.debug("Service aspect initialized");
        return new ServiceAspect();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("mail.port"))));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.debug", env.getProperty("mail.debug"));
        log.debug("MailSender initialized");
        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateOrderMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(Objects.requireNonNull(env.getProperty("mail.username")));
        log.debug("SimpleMailMessage initialized");
        return message;
    }
}
