package br.com.estampamente.entities.DTOs;

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

    @JsonProperty("discount_percent")
    private Integer discountPercent;
    private String brand;
    private String image;
}
