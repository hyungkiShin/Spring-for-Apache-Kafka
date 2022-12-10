package practice.chap1clip1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(id = "practice-id", topics = "quickstart-events")
    public void listen(String message) {
        System.out.println("================================================");
        System.out.println("Received Messasge in group practice-id: " + message);
        System.out.println("================================================");
    }
}
