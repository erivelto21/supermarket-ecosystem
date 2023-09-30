package br.com.product.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class TestSupport {
    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.product.templates");
    }
}
