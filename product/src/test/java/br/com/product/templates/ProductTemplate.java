package br.com.product.templates;

import br.com.product.domains.Product;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Arrays;
import java.util.UUID;

import static br.com.product.templates.Templates.VALID;
import static br.com.product.templates.Templates.VALID_WITH_ID;

public class ProductTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Product.class).addTemplate(VALID.name(), new Rule() {{
            add("name", "Product name");
            add("description", "A Product with a description");
            add("brand", "Brand");
            add("department", "Department");
            add("type", "Type");
            add("traderCode", "AB1235");
            add("traderId", 12345L);
            add("weightInGram", 1000);
            add("materials", Arrays.asList("Material 1", "Material 2", "Material 3", "Material 4"));
            add("imageUrls", Arrays.asList("imageUrl 1", "imageUrl 2", "imageUrl 3", "imageUrl 4"));
        }}).addTemplate(VALID_WITH_ID.name()).inherits(VALID.name(), new Rule(){{
            add("id", UUID.randomUUID().toString());
        }});
    }
}
