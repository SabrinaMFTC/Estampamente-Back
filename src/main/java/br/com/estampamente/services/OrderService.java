package br.com.estampamente.services;

import br.com.estampamente.entities.*;
import br.com.estampamente.entities.DTOs.CheckoutRequestDTO;
import br.com.estampamente.entities.DTOs.ItemDTO;
import br.com.estampamente.entities.DTOs.OrderDTO;
import br.com.estampamente.entities.DTOs.OrderItemDTO;
import br.com.estampamente.entities.enums.PaymentMethod;
import br.com.estampamente.repositories.ClientRepository;
import br.com.estampamente.repositories.ItemRepository;
import br.com.estampamente.repositories.OrderRepository;
import br.com.estampamente.utils.DtoConverters;
import br.com.estampamente.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,ClientRepository clientRepository,ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository=itemRepository;
        this.clientRepository=clientRepository;
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



    public Order createOrder(CheckoutRequestDTO dto) {

        Optional<Client> existingClientOpt = clientRepository.findByEmail(dto.getEmail());
        Client client = existingClientOpt.orElseGet(() -> {
            Client newClient = new Client();
            newClient.setName(dto.getName());
            newClient.setEmail(dto.getEmail());
            newClient.setPhoneNumber(dto.getPhone());
            newClient.setPassword(PasswordUtils.hashPassword("12345"));
            Address address = new Address();
            address.setCity(dto.getCity());
            address.setCountry(dto.getCountry());
            address.setNumber(123);
            address.setZipCode(dto.getCep());
            address.setClient(newClient);
            return clientRepository.save(newClient);
        });


        Order order = new Order();
        order.setClient(client);
        order.setTotalAmount(dto.getTotal());
        order.setStatus("COMPLETED");
        order.setPaymentMethod(PaymentMethod.CARD);



        for (OrderItemDTO itemDto : dto.getItems()) {
            Item item = itemRepository.findById(itemDto.getId())
                    .orElseThrow(() -> new RuntimeException("Item não encontrado: ID " + itemDto.getId()));



            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setItemQuantity(itemDto.getQuantity());

            order.getOrderItems().add(orderItem);
        }


        return orderRepository.save(order);
    }
}
