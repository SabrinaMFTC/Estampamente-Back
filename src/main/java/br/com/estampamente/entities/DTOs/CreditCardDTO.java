package br.com.estampamente.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardDTO {
    private Integer id;
    private String description;
    private Integer cvv;
    private String title;
}
