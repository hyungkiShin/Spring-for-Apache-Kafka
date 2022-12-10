package practice.chap1clip1;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class Chap1Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Clip1Application.class, args);
    }


    @Bean
    public ApplicationRunner runner(AdminClient adminClient) {
        return args -> {
            Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
            for (String topicName : topics.keySet()) {
                TopicListing topicListing = topics.get(topicName);
                // 토픽명, internal 여부, 파티션 수, 복제본 수
                System.out.println("topicListing = " + topicListing);

                // 세부정보 확인
                Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName)).all().get();
                System.out.println("description = " + description);

                // 토픽 삭제 (내부 토픽은 삭제하지 않는다)
                if (!topicListing.isInternal()) {
                    adminClient.deleteTopics(Collections.singleton(topicName));
                }
            }
        };
    }

}
