package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//mandatory to tell SC , following is java config class ,as a replacement of xml config file
@ComponentScan(basePackages = {"dependent","dependency"})
public class AppConfig {
//Later we will add @Bean annotated method(equivalent to <bean id class...>  :in  spring sec
}
