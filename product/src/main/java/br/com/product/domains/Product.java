package br.com.product.domains;

import java.util.Set;
import lombok.Data;

@Data
public class Product {

  private String id;
  private String name;
  private String description;
  private String brand;
  private String department;
  private String type;
  private String traderCode;
  private long traderId;
  private int weightInGram;
  private Set<String> materials;
  private Set<String> imageUrls;
}
