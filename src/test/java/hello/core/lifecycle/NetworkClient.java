package hello.core.lifecycle;



import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class NetworkClient {
    //인터페이스를 사용한 초기화방식은 거의 사용하지 않음
//public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;


    public NetworkClient() {
        System.out.println("생성자호출, url= " + url); //url없음

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url); //url널
    }

    public void call(String message) {
        System.out.println("call: " + url + " message " + message); //url널

    }

    //서비스 종료시 호출
    public void disConnect() {
        System.out.println("close:" + url);
    }


    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }





/*    //의존관계주입이 끝나면 호출해줌
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disConnect();
    }*/
}
