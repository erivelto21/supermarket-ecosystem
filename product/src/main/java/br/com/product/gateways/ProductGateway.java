package br.com.product.gateways;

import br.com.product.domains.Product;

public interface ProductGateway {

  void save(Product product);

  Product findByTraderCodeAndTraderId(String traderCode, long traderId);
}
