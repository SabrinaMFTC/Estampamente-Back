package br.com.estampamente.controllers;

import br.com.estampamente.entities.Client;
import br.com.estampamente.entities.DTOs.OrderDTO;
import br.com.estampamente.entities.Order;
import br.com.estampamente.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(Authentication authentication) {
       try{
           Client client = (Client) authentication.getPrincipal();
           return new ResponseEntity<>( orderService.getAllOrders(client.getId()),HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id, Authentication authentication) {
        try{
            logger.info("Pedido n. {}", id);
            return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
