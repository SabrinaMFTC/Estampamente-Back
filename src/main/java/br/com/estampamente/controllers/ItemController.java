package br.com.estampamente.controllers;

import br.com.estampamente.entities.DTOs.ItemDTO;
import br.com.estampamente.entities.enums.ItemType;
import br.com.estampamente.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/items")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService= itemService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<ItemDTO>> getAllItemsByType(@PathVariable(name = "type") String type) {
        try {
            List<ItemDTO> items = itemService.getAllItemsByType(ItemType.valueOf(type.toUpperCase()));
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/camiseta/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(name = "id") Long id) {
        try {
            ItemDTO item = itemService.getItemById(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Item not found for id: {}",id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
