package br.com.product.usecases;

import static java.util.Objects.isNull;
import static net.logstash.logback.argument.StructuredArguments.v;

import br.com.product.domains.Product;
import br.com.product.gateways.ProductGateway;
import br.com.product.utils.LogKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateProduct {

  private final ProductGateway productGateway;

  public void execute(final Product product) {
    final String traderCode = product.getTraderCode();
    final long traderId = product.getTraderId();

    final Product productFromDatabase = productGateway.findByTraderCodeAndTraderId(traderCode,
        traderId);

    if (isNull(productFromDatabase)) {
      createProduct(product);
      return;
    }

    log.info("Product with trade code {} already exist for trader {}",
        v(LogKey.TRADER_CODE.toString(), traderCode),
        v(LogKey.TRADER_ID.toString(), traderId));
  }

  private void createProduct(final Product product) {
    productGateway.save(product);

    log.info("Product {} created for trader {}",
        v(LogKey.TRADER_CODE.toString(), product.getTraderCode()),
        v(LogKey.TRADER_ID.toString(), product.getTraderId()),
        product);
  }
}
