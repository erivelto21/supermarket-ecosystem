package br.com.product.mappers;

import br.com.product.domains.Product;
import br.com.product.domains.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDTOMapper {
    ProductDTOMapper INSTANCE = Mappers.getMapper(ProductDTOMapper.class);

    Product mapToDomain(ProductDTO productDTO);
}
