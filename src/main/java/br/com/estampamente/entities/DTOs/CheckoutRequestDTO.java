package br.com.estampamente.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckoutRequestDTO {

    private String name;
    private String email;
    private String phone;
    private String cep;
    private String secondaryFone;
    private String city;
    private String country;
    private Double total;

    private List<OrderItemDTO> items;
}
