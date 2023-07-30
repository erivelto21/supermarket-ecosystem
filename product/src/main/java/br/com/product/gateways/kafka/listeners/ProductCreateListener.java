package br.com.product.gateways.kafka.listeners;

import static net.logstash.logback.argument.StructuredArguments.v;

import br.com.product.domains.Product;
import br.com.product.gateways.kafka.listeners.mappers.ProductCreateResourceMapper;
import br.com.product.gateways.kafka.listeners.resources.ProductCreateResource;
import br.com.product.usecases.CreateProduct;
import br.com.product.utils.JsonUtils;
import br.com.product.utils.LogKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Component
@RequiredArgsConstructor
@Validated
public class ProductCreateListener {

  private final CreateProduct createProduct;
  private final JsonUtils jsonUtils;

  @KafkaListener(
      topics = "${spring.kafka.consumer.topics.product-create:product.create.1}",
      groupId = "${spring.kafka.consumer.group-id}",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void listen(
      @Header(KafkaHeaders.RECEIVED_TOPIC) final String topic,
      @Header(KafkaHeaders.RECEIVED_PARTITION) final String partition,
      @Header(KafkaHeaders.RECEIVED_KEY) final String key,
      @Header(KafkaHeaders.RECEIVED_TIMESTAMP) final String timestamp,
      @Header(KafkaHeaders.OFFSET) final long offset,
      @Payload final String payload) {

    final ProductCreateResource productCreateResource = jsonUtils.jsonToObject(payload,
        ProductCreateResource.class);

    log.info(
        "Received message on topic/key/partition/offset/timestamp ({}/{}/{}/{}/{}) of trader: {} ",
        v(LogKey.TOPIC.toString(), topic),
        v(LogKey.KEY.toString(), key),
        v(LogKey.PARTITION.toString(), partition),
        v(LogKey.OFFSET.toString(), offset),
        v(LogKey.MSG_RECEIVED_TIMESTAMP.toString(), timestamp),
        v(LogKey.TRADER_ID.toString(), productCreateResource.traderId()),
        productCreateResource);

    final Product product = ProductCreateResourceMapper.INSTANCE.toDomain(productCreateResource);

    createProduct.execute(product);
  }
}
