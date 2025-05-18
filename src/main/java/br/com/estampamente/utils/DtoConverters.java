package br.com.estampamente.utils;

import br.com.estampamente.entities.DTOs.OrderDTO;
import br.com.estampamente.entities.Order;

public class DtoConverters {

    public static OrderDTO toDto(Order order) {
        return new OrderDTO(
                order.getId(),
               "Pedido n. " + order.getId(),
                order.getOrderDate(),
                order.getStatus()
        );
    }

}
