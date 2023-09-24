package br.com.product.gateways.mongodb;

import br.com.product.domains.Product;
import br.com.product.gateways.ProductGateway;
import br.com.product.gateways.mongodb.documents.ProductDocument;
import br.com.product.gateways.mongodb.mappers.ProductDocumentMapper;
import br.com.product.gateways.mongodb.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductMongodbGatewayImpl implements ProductGateway {

  private final ProductRepository productRepository;

  @Override
  public Optional<Product> save(final Product product) {
    final ProductDocument productDocument = ProductDocumentMapper.INSTANCE.toDocument(product);

    return Optional.of(ProductDocumentMapper.INSTANCE.toDomain(productDocument));
  }

  @Override
  public Optional<Product> findByTraderCodeAndTraderId(final String traderCode, final long traderId) {
    final ProductDocument productDocument = productRepository.findOneByTraderCodeAndTraderId(
        traderCode, traderId);

    return Optional.of(ProductDocumentMapper.INSTANCE.toDomain(productDocument));
  }
}
