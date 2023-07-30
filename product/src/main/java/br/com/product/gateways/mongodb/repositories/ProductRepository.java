package br.com.product.gateways.mongodb.repositories;

import br.com.product.gateways.mongodb.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {

  ProductDocument findOneByTraderCodeAndTraderId(String traderCode, long traderId);
}
