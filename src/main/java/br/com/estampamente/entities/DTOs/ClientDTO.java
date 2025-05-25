package br.com.estampamente.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String email;
    private String nome;

    private String phoneNumber;
    private List<AddressDTO> address;
    private List<OrderDTO> orders;
    private List<CreditCardDTO> cards;
}
