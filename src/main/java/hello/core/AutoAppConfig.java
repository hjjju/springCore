package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //지정하지않으면 Componentscan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)  //default:여기서부터 뒤짐 hello.core
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemoryMemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }


}


