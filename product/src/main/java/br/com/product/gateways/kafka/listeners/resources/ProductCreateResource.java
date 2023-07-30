package br.com.product.gateways.kafka.listeners.resources;

import java.io.Serializable;
import java.util.Set;

public record ProductCreateResource(
    String name,
    String description,
    String brand,
    String department,
    String type,
    String traderCode,
    long traderId,
    int weightInGram,
    Set<String> materials,
    Set<String> imageUrls) implements Serializable { }
