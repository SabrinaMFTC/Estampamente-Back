package br.com.estampamente.entities.DTOs;

import br.com.estampamente.entities.enums.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private ItemType type;
    @JsonProperty("discount_percent")
    private Integer discountPercent;
    private String brand;
    private String image;
}
