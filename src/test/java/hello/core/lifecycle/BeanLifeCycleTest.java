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

        /**
         * 설정 정보 사용특징
         * 1.메서드 이름을 자유롭게 줄 수 있다.
         * 2. 스프링빈이 스프링 코드에 의존하지 않는다.
         * 3. 코드가 아니라 설정 정보를 사용하기 때문에 고칠 수 없는 외부라이브러리에도 초기화 , 종료 메서드를 사용 할 수 있다. ****
         */
//        @Bean(initMethod = "init",destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); //객체 생성후
            networkClient.setUrl("http:hello-spring.dev");
            return networkClient;
        }

        /*@Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); //객체 생성후
            networkClient.setUrl("http:hello-spring.dev");
            return networkClient;
        }*/

    }


}
