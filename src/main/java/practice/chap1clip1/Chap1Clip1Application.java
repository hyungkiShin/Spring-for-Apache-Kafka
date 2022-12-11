package practice.chap1clip1;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import practice.chap1clip1.producer.ClipProducer;

import java.nio.charset.StandardCharsets;


@SpringBootApplication
public class Chap1Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Clip1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer) {
        return args -> {
            // 기본적으로 async 로 처리가 되는데, 명확하게 보기 위해선 별도의 처리가 필요하다.
            clipProducer.async("clip3", "Hello, Clip3-async");
            clipProducer.sync("clip3", "Hello, Clip3-sync");
//            Thread.sleep(1000L);
            clipProducer.routingSend("clip3", "Hello, Clip3-routingSend");
            clipProducer.routingSendBytes("clip3-bytes", "Hello, Clip3-bytes".getBytes(StandardCharsets.UTF_8));
            clipProducer.replyingSend("clip3-request", "Ping Clip3");
        };
    }


}
