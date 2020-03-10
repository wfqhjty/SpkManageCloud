package cn.spk.data.kafka.provider;

import cn.spk.data.kafka.message.Message;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

//@Component
public class KafkaProvider {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProvider.class);

    /**
     * TOPIC
     */
    private static final String TOPIC = "test";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Message message) {


        // 发送消息
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, JSONObject.toJSONString(message));
        // 监听回调
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("## Send message fail ...");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("## Send message success ...");
            }
        });
    }

}
