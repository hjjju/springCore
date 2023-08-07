package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    //인스턴스의 참조를 꺼낼수 있는 방법은 얘밖에 없음
    public static SingletonService getInstance() {
        return instance;

    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
