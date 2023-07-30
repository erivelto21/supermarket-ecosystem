package br.com.product.gateways.mongodb;

import br.com.product.domains.Product;
import br.com.product.gateways.ProductGateway;
import br.com.product.gateways.mongodb.documents.ProductDocument;
import br.com.product.gateways.mongodb.mappers.ProductDocumentMapper;
import br.com.product.gateways.mongodb.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMongodbGatewayImpl implements ProductGateway {

  private final ProductRepository productRepository;

  @Override
  public void save(final Product product) {
    final ProductDocument productDocument = ProductDocumentMapper.INSTANCE.toDocument(product);

    productRepository.save(productDocument);
  }

  @Override
  public Product findByTraderCodeAndTraderId(final String traderCode, final long traderId) {
    final ProductDocument productDocument = productRepository.findOneByTraderCodeAndTraderId(
        traderCode, traderId);

    return ProductDocumentMapper.INSTANCE.toDomain(productDocument);
  }
}
