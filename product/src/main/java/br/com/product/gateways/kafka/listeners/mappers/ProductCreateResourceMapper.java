package br.com.product.gateways.kafka.listeners.mappers;

import br.com.product.domains.dtos.ProductDTO;
import br.com.product.gateways.kafka.listeners.resources.ProductCreateResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCreateResourceMapper {

  ProductCreateResourceMapper INSTANCE = Mappers.getMapper(ProductCreateResourceMapper.class);

  ProductDTO mapToDTO(ProductCreateResource productCreateResource);
}
