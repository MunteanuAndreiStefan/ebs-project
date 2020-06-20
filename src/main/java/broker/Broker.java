package broker;

import matching.MatchingDTO;
import matching.MatchingMechanism;
import com.ebs.project.proto.PublicationMessage;
import com.ebs.project.proto.SubscriptionMessage;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import rabbit.RMQConstants;
import rabbit.RMQSinkPublisher;

import java.io.IOException;

public class Broker {


    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        RMQConnectionConfig connectionConfig = RMQConstants.RMQ_CONFIG;

        RMQSource<SubscriptionMessage.SubscriptionDTO> subscriptionsSource = getSubscriptionsSource(connectionConfig);
        var subscriptionStream = env.addSource(subscriptionsSource).setParallelism(1);
        subscriptionStream.addSink(getSubscriptionSink());

        AbstractDeserializationSchema<PublicationMessage.PublicationDTO> publicationDTODeserializer = getPublicationDTODeserializer();
        var publicationStream =
                env.addSource(new RMQSource<>(connectionConfig, RMQConstants.PUBLICATIONS_QUEUE, true, publicationDTODeserializer)).setParallelism(1);

        publicationStream
                .map((MapFunction<PublicationMessage.PublicationDTO, Object>) value -> new MatchingDTO(value,
                        MatchingMechanism.getSubscriptionsBy(value)))
                .addSink(new RMQSinkPublisher<>());

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static AbstractDeserializationSchema<PublicationMessage.PublicationDTO> getPublicationDTODeserializer() {
        return new AbstractDeserializationSchema<>() {
            @Override
            public PublicationMessage.PublicationDTO deserialize(byte[] message) throws IOException {
                PublicationMessage.PublicationDTO publicationDTO = PublicationMessage.PublicationDTO.parseFrom(message);
                System.out.println(publicationDTO);
                return publicationDTO;
            }
        };
    }


    private static RMQSource<SubscriptionMessage.SubscriptionDTO> getSubscriptionsSource(RMQConnectionConfig connectionConfig) {
        return new RMQSource<>(connectionConfig, RMQConstants.SUBSCRIPTIONS_QUEUE, true,
                new AbstractDeserializationSchema<>() {
                    @Override
                    public SubscriptionMessage.SubscriptionDTO deserialize(byte[] bytes) throws IOException {
                        return SubscriptionMessage.SubscriptionDTO.parseFrom(bytes);
                    }
                }
        );
    }

    private static SinkFunction<SubscriptionMessage.SubscriptionDTO> getSubscriptionSink() {
        return new SinkFunction<>() {
            @Override
            public void invoke(SubscriptionMessage.SubscriptionDTO value, Context context) {
                MatchingMechanism.addSubscription(value);
                System.out.println(value.toString());
            }
        };
    }
}

