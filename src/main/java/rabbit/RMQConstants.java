package rabbit;

import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

public class RMQConstants {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 5673;
    public static final String VIRTUAL_HOST = "/";
    public static final String USERNAME = "guest";
    public static final String PASSWORD = "guest";

    public static final String PUBLICATIONS_QUEUE = "publications_queue";
    public static final String SUBSCRIPTIONS_QUEUE = "subscriptions_queue";

    public static final RMQConnectionConfig RMQ_CONFIG = new RMQConnectionConfig.Builder()
            .setHost(HOST)
            .setPort(PORT)
            .setVirtualHost(VIRTUAL_HOST)
            .setUserName(USERNAME)
            .setPassword(PASSWORD)
            .build();

}
