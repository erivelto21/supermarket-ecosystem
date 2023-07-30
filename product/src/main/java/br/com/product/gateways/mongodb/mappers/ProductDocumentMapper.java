package br.com.product.gateways.mongodb.mappers;

import br.com.product.domains.Product;
import br.com.product.gateways.mongodb.documents.ProductDocument;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDocumentMapper {

  ProductDocumentMapper INSTANCE = Mappers.getMapper(ProductDocumentMapper.class);

  Product toDomain(ProductDocument productDocument);

  @InheritInverseConfiguration
  ProductDocument toDocument(Product product);
}
