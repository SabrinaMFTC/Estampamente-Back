package br.com.estampamente.services;

import br.com.estampamente.entities.DTOs.OrderDTO;
import br.com.estampamente.entities.Order;
import br.com.estampamente.repositories.OrderRepository;
import br.com.estampamente.utils.DtoConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrders(Long id) {
        List<Order> orders = orderRepository.findByClientId(id);
        if(orders.isEmpty()){
            return List.of();
        }
        return orders.stream()
                .map(DtoConverters::toDto)
                .toList();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }
}
