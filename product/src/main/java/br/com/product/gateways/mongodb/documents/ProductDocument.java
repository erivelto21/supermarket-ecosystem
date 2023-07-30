package br.com.product.gateways.mongodb.documents;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("product")
public class ProductDocument implements Serializable {

  @Serial
  private static final long serialVersionUID = -346140259373796744L;
  @Id
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
