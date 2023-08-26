package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //close는 AppplicationContext가 제공x ->하위까지내려와야함

    }

    @Configuration
    static class LifeCycleConfig{

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); //객체 생성후
            networkClient.setUrl("http:hello-spring.dev");
            return networkClient;
        }

    }


}
