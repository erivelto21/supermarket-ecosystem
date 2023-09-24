package br.com.product.domains.dtos;

import java.util.Set;

public record ProductDTO(String name, String description, String brand, String department, String type,
                         String traderCode, long traderId, int weightInGram, Set<String> materials,
                         Set<String> imageUrls) {
}
