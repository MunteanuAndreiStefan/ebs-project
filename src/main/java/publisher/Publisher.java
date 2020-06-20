/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package publisher;

import com.ebs.project.proto.PublicationMessage;
import homework.DataGenerator;
import homework.models.PublicationVO;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import rabbit.RMQConstants;
import rabbit.RMQSinkImpl;
import rabbit.RMQSinkPubOptionsImpl;

import java.util.List;

public class Publisher {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        RMQConnectionConfig connectionConfig = RMQConstants.RMQ_CONFIG;

        RMQSinkPubOptionsImpl<PublicationVO> options = new RMQSinkPubOptionsImpl<>(RMQConstants.PUBLICATIONS_QUEUE);
        SerializationSchema<PublicationVO> publicationMessageSerializer = getPublicationMessageSerializer();
        RMQSinkImpl<PublicationVO> rmqSink = new RMQSinkImpl<>(connectionConfig, publicationMessageSerializer, options);

        List<PublicationVO> publicationsList = DataGenerator.getPublications();
        var publications = env.fromCollection(publicationsList);
        publications.addSink(rmqSink);
        publications.print();
        env.execute();
    }

    private static SerializationSchema<PublicationVO> getPublicationMessageSerializer() {
        return (SerializationSchema<PublicationVO>) publicationVO -> {

            PublicationMessage.PublicationDTO.Builder builder = PublicationMessage.PublicationDTO.newBuilder();
            builder.setCompanyName(publicationVO.getCompanyName());
            builder.setDrop(publicationVO.getCompanyDrop());
            builder.setValue(publicationVO.getCompanyValue());
            builder.setVariation(publicationVO.getCompanyVariation());
            builder.setDate(publicationVO.getCompanyDate());

            return builder.build().toByteArray();
        };
    }
}
