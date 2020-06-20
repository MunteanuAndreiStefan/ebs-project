package rabbit;

import com.rabbitmq.client.AMQP;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSinkPublishOptions;

import java.util.UUID;

public class RMQSinkPubOptionsImpl<T> implements RMQSinkPublishOptions<T> {

    private final String queueName;

    public RMQSinkPubOptionsImpl(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String computeRoutingKey(Object o) {
        return queueName;
    }

    @Override
    public AMQP.BasicProperties computeProperties(Object o) {
        return new AMQP.BasicProperties().builder().correlationId(UUID.randomUUID().toString()).build();
    }

    @Override
    public String computeExchange(Object o) {
        return "";
    }
}
