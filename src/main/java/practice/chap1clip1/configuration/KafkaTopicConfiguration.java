package practice.chap1clip1.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    public NewTopic clip2() {
        return TopicBuilder.name("clip2").build();
    }

    /**
     * 토픽을 생성할 때, 토픽의 설정을 추가할 수 있다
     * 카프카 어드민이 초기화 되면서 토픽을 생성한다
     * kafkaAdmin 을 빈으로 등록하면, 카프카 어드민이 초기화 되면서 토픽을 생성한다
     * springboot 에서 autoconfigure 를 통해 자동으로 카프카 어드민을 빈으로 등록한다
     * context 가 초기화 될때 initialize 라는 메서드가 실행하게 되는데, 이때 토픽을 생성한다
     * @return
     */
    @Bean
    public KafkaAdmin.NewTopics clip2s() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("clip2-part1").build(),
                // 1 시간의 retention time 을 가지는 topic 생성
                TopicBuilder.name("clip2-part2")
                        .partitions(3) // 3개 파티션
                        .replicas(1) // 1개 복제본
                        .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000 * 60 * 60)) // 1시간
                        .build()
        );
    }
}
