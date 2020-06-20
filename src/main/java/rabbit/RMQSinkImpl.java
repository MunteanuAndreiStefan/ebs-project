package rabbit;

import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSink;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSinkPublishOptions;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

public class RMQSinkImpl<T> extends RMQSink<T> {

    public RMQSinkImpl(RMQConnectionConfig rmqConnectionConfig, SerializationSchema<T> schema, RMQSinkPublishOptions<T> publishOptions) {
        super(rmqConnectionConfig, schema, publishOptions);
    }

}
