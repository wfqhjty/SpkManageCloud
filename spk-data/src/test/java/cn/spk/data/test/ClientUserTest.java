package cn.spk.data.test;

import cn.spk.data.kafka.message.Message;
import cn.spk.data.kafka.provider.KafkaProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClientUserTest {

    @Autowired
    private KafkaProvider kafkaProvider;

    @Test
    public void sendMessage() {

        // 发送 10 个消息
        for (int i = 0; i < 10; i++) {
            String orderId = i + "--";
            String orderNum = UUID.randomUUID().toString();
            Message message = new Message();
            message.setId(orderNum);
            message.setMessage(orderId);
            kafkaProvider.sendMessage(message);
        }
    }
}