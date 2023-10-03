package br.com.product.usecases

import br.com.product.domains.Product
import br.com.product.gateways.ProductGateway
import br.com.product.mappers.ProductDTOMapper
import br.com.product.support.Spec
import br.com.six2six.fixturefactory.Fixture

import static br.com.product.templates.Templates.VALID
import static br.com.product.templates.Templates.VALID_WITH_ID

class CreateProductSpec extends Spec {
    CreateProduct createProduct

    ProductGateway productGateway

    def setup() {
        productGateway = Mock()

        createProduct = new CreateProduct(productGateway)
    }

    def "Should create a product when the combination of trader code and trader id is unique"() {
        given: "A product to be created"
        Product productToBeCreated = Fixture.from(Product.class).gimme(VALID.name())
        def productDTO = ProductDTOMapper.INSTANCE.mapFromDomain(productToBeCreated)

        and: "Not exist a product with the same trader code and trader id"
        def traderCode = productToBeCreated.getTraderCode()
        def traderId = productToBeCreated.getTraderId()
        productGateway.findByTraderCodeAndTraderId(traderCode, traderId) >> Optional.empty()

        and: "A product should be created"
        def createdProduct = ProductDTOMapper.INSTANCE.mapToDomain(productDTO)
        createdProduct.setId(UUID.randomUUID().toString())
        productGateway.save(_ as Product) >> Optional.of(createdProduct)

        when: "A product is being created"
        def result = createProduct.execute(productDTO)

        then:
        result.isPresent()
        def productResult = result.get()
        !productResult.id.empty
        productResult.name == productToBeCreated.name
        productResult.description == productToBeCreated.description
        productResult.brand == productToBeCreated.brand
        productResult.department == productToBeCreated.department
        productResult.type == productToBeCreated.type
        productResult.traderCode == productToBeCreated.traderCode
        productResult.traderId == productToBeCreated.traderId
        productResult.weightInGram == productToBeCreated.weightInGram
        productResult.materials == productToBeCreated.materials
        productResult.imageUrls == productToBeCreated.imageUrls
    }

    def "Should not create a product when the combination of trader code and trader id isn't unique"() {
        given: "A product to be created"
        Product productToBeCreated = Fixture.from(Product.class).gimme(VALID.name())
        def productDTO = ProductDTOMapper.INSTANCE.mapFromDomain(productToBeCreated)

        and: "Exist a product with the same trader code and trader id"
        def traderCode = productToBeCreated.getTraderCode()
        def traderId = productToBeCreated.getTraderId()

        def databaseProduct = Fixture.from(Product.class).gimme(VALID_WITH_ID.name())
        productGateway.findByTraderCodeAndTraderId(traderCode, traderId) >> Optional.of(databaseProduct)

        and: "A product shouldn't be created"
        productGateway.save(_ as Product) >> Optional.empty()

        when: "A product is being created"
        def result = createProduct.execute(productDTO)

        then:
        result.isEmpty()
    }
}
