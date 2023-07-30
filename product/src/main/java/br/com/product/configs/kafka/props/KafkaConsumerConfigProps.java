package br.com.product.configs.kafka.props;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerConfigProps {

  private String bootstrapServers;
  private String groupId;
  private String keyDeserializer;
  private String valueDeserializer;
}
