package rabbit;

import com.ebs.project.proto.SubscriptionMessage;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import matching.MatchingDTO;
import org.apache.flink.shaded.netty4.io.netty.util.internal.StringUtil;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

import java.util.UUID;

public class RMQSinkPublisher<IN> extends RichSinkFunction<IN> {

    @Override
    public void invoke(Object genericValue, Context context) {
        var value = (MatchingDTO) genericValue;
        try {
            value.getSubscriptionDTOS().forEach(subscriptionDTO -> publish(value, subscriptionDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void publish(MatchingDTO value, SubscriptionMessage.SubscriptionDTO subscriptionDTO) {
        try {
            RMQConnectionConfig rmqConnectionConfig = new RMQConnectionConfig.Builder()
                    .setHost(subscriptionDTO.getHost())
                    .setVirtualHost(RMQConstants.VIRTUAL_HOST)
                    .setUserName(RMQConstants.USERNAME)
                    .setPassword(RMQConstants.PASSWORD)
                    .setPort(subscriptionDTO.getPort())
                    .build();

            ConnectionFactory factory = rmqConnectionConfig.getConnectionFactory();
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            if (subscriptionDTO.getQueueName() != null) {
                channel.queueDeclare(subscriptionDTO.getQueueName(), true, false, false, null);
            }
            byte[] msg = value.getPublicationDTO().toByteArray();

            AMQP.BasicProperties amqpBasicProps = new AMQP.BasicProperties().builder().correlationId(UUID.randomUUID().toString()).build();

            channel.basicPublish(StringUtil.EMPTY_STRING, subscriptionDTO.getQueueName(), false, false, amqpBasicProps, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
