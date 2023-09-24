package br.com.product.usecases;

import br.com.product.domains.Product;
import br.com.product.gateways.ProductGateway;
import br.com.product.utils.LogKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.v;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateProduct {

  private final ProductGateway productGateway;

  public Optional<Product> execute(final Product product) {
    final String traderCode = product.getTraderCode();
    final long traderId = product.getTraderId();

    final Optional<Product> productFromDatabase = productGateway.findByTraderCodeAndTraderId(traderCode,
            traderId);

    if (productFromDatabase.isEmpty()) {
      return createProduct(product);
    }

    log.info("Product with trade code {} already exist for trader {}",
        v(LogKey.TRADER_CODE.toString(), traderCode),
        v(LogKey.TRADER_ID.toString(), traderId));

    return Optional.empty();
  }

  private Optional<Product> createProduct(final Product productToBeCreated) {
    final Optional<Product> product = productGateway.save(productToBeCreated);

    log.info("Product {} created for trader {}",
        v(LogKey.TRADER_CODE.toString(), productToBeCreated.getTraderCode()),
        v(LogKey.TRADER_ID.toString(), productToBeCreated.getTraderId()),
        productToBeCreated);

    return product;
  }
}
