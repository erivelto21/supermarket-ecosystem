package br.com.product.gateways.mongodb;

import br.com.product.domains.Product;
import br.com.product.gateways.mongodb.documents.ProductDocument;
import br.com.product.gateways.mongodb.mappers.ProductDocumentMapper;
import br.com.product.gateways.mongodb.repositories.ProductRepository;
import br.com.product.support.MockitoTestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.product.templates.Templates.VALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductMongodbGatewayImplTest extends MockitoTestSupport {
    @InjectMocks
    private ProductMongodbGatewayImpl productMongodbGateway;

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldSaveProduct() {
        final Product productToBeSaved = Fixture.from(Product.class).gimme(VALID.name());

        final ProductDocument document = ProductDocumentMapper.INSTANCE.toDocument(productToBeSaved);
        when(productRepository.save(any(ProductDocument.class))).thenReturn(document);

        final Optional<Product> result = productMongodbGateway.save(productToBeSaved);

        verify(productRepository).save(any(ProductDocument.class));

        Assertions.assertTrue(result.isPresent());
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(productToBeSaved);
    }

    @Test
    void shouldFindByTraderCodeAndTraderId() {
        final Product savedProduct = Fixture.from(Product.class).gimme(VALID.name());
        final ProductDocument document = ProductDocumentMapper.INSTANCE.toDocument(savedProduct);

        final String traderCode = document.getTraderCode();
        final long traderId = document.getTraderId();

        when(productRepository.findOneByTraderCodeAndTraderId(traderCode, traderId)).thenReturn(document);

        final Optional<Product> result = productMongodbGateway.findByTraderCodeAndTraderId(traderCode, traderId);

        verify(productRepository).findOneByTraderCodeAndTraderId(traderCode, traderId);

        Assertions.assertTrue(result.isPresent());
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(savedProduct);
    }
}
