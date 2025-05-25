package br.com.estampamente.utils;

import br.com.estampamente.entities.Address;
import br.com.estampamente.entities.CreditCard;
import br.com.estampamente.entities.DTOs.AddressDTO;
import br.com.estampamente.entities.DTOs.CreditCardDTO;
import br.com.estampamente.entities.DTOs.OrderDTO;
import br.com.estampamente.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverters {

    public static OrderDTO toDto(Order order) {
        return new OrderDTO(
                order.getId(),
               "Pedido n. " + order.getId(),
                order.getOrderDate(),
                order.getStatus()
        );
    }


    public static AddressDTO toAddressDto(Address address) {
        if (address == null) return null;
        return new AddressDTO(
                address.getId(),
                address.getCity(),
                address.getCountry()
        );
    }


    public static List<AddressDTO> toAddressDtoList(List<Address> addresses) {
        if (addresses == null) return List.of();
        return addresses.stream()
                .map(DtoConverters::toAddressDto)
                .collect(Collectors.toList());
    }



    public static CreditCardDTO toCreditCardDto(CreditCard card) {
        if (card == null) return null;
        return new CreditCardDTO(
                card.getId(),
                card.getCardNumber(),
                card.getCvv(),
                card.getCardName()
        );
    }


    public static List<CreditCardDTO> toCreditCardDtoList(List<CreditCard> cards) {
        if (cards == null || cards.isEmpty()) {
            return List.of();
        }
        return cards.stream()
                .map(DtoConverters::toCreditCardDto)
                .collect(Collectors.toList());
    }

}
