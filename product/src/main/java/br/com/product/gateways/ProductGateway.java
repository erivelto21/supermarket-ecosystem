package br.com.product.gateways;

import br.com.product.domains.Product;

import java.util.Optional;

public interface ProductGateway {

  Optional<Product> save(Product product);

  Optional<Product> findByTraderCodeAndTraderId(String traderCode, long traderId);
}
