package br.com.product.gateways.kafka.listeners;

import br.com.product.domains.Product;
import br.com.product.domains.dtos.ProductDTO;
import br.com.product.mappers.ProductDTOMapper;
import br.com.product.support.MockitoTestSupport;
import br.com.product.usecases.CreateProduct;
import br.com.product.utils.JsonUtils;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static br.com.product.templates.Templates.VALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class ProductCreateListenerTest extends MockitoTestSupport {
    @InjectMocks
    private ProductCreateListener productCreateListener;

    @Spy
    private JsonUtils jsonUtils;

    @Mock
    private CreateProduct createProduct;

    @Captor
    private ArgumentCaptor<ProductDTO> productDTOArgumentCaptor;

    @Test
    void shouldListenProductJsonPayload() {
        final Product product = Fixture.from(Product.class).gimme(VALID.name());
        final String expectedResult = jsonUtils.objectToJson(ProductDTOMapper.INSTANCE.mapFromDomain(product));

        final String payload = jsonUtils.objectToJson(product);

        productCreateListener.listen("topic", "01", "key", "timestamp", 12, payload);

        verify(createProduct).execute(productDTOArgumentCaptor.capture());

        final String result = jsonUtils.objectToJson(productDTOArgumentCaptor.getValue());
        assertThat(expectedResult).isEqualTo(result);
    }
}
