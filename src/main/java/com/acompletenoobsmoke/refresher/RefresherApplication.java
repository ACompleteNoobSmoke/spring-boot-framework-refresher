package com.acompletenoobsmoke.refresher;

import com.acompletenoobsmoke.refresher.person.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;


@SpringBootApplication
@EnableScheduling
@EnableAsync
public class RefresherApplication {

    @Value("${app.stripe.api-key}")
    private  String stripeAPIKEY;

    public static void main(String[] args) {
        SpringApplication.run(RefresherApplication.class, args);
        System.out.println("Hello World");
        LOGGER.info("Hello World -- ACompleteNoobSmoke Logger");
        LOGGER.warn("Hello World -- ACompleteNoobSmoke Logger (Warning)");
    }

    public Set<String> getAllApplicationPropertyKeys(ConfigurableEnvironment environment) {
        Set<String> keys = new HashSet<>();
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            for (PropertySource<?> propertySource : configurableEnvironment.getPropertySources()) {
                if (propertySource instanceof EnumerablePropertySource) {
                    EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) propertySource;
                    for (String key : enumerablePropertySource.getPropertyNames()) {
                        keys.add(key);
                    }
                }
            }
        }
        return keys;
    }

    @Bean
    CommandLineRunner commandlineRunner(ObjectMapper objectMapper, Environment environment, StipeConfig stripeConfig) throws JsonProcessingException {
        String personString = "{\"id\":1,\"name\":\"Rock Lee\",\"age\":18}";
        Person person = objectMapper.readValue(personString, Person.class);
        System.out.println(stripeConfig);
        System.out.println(person);
        System.out.println(objectMapper.writeValueAsString(person));
        return args -> {

        };
    }

//    @Scheduled(
//            fixedRate = 10,
//            timeUnit = TimeUnit.SECONDS
//    )
//    @Async
//    public void sendEmails() throws InterruptedException {
//        System.out.println("Start Sending Emails");
//        Thread.sleep(2000);
//        System.out.println("End Sending Emails");
//    }
//
//    @Scheduled(cron = "*/5 * * * * *")
//    @Async
//    public void generateSalesReports() throws InterruptedException {
//        System.out.println("Start Generating Sales Reports");
//        Thread.sleep(5000);
//        System.out.println("End Generating Sales Reports");
//    }

}
