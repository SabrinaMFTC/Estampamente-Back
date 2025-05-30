package br.com.estampamente.services;

import br.com.estampamente.entities.DTOs.ItemDTO;
import br.com.estampamente.entities.enums.ItemType;
import br.com.estampamente.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> getAllItemsByType(ItemType type) {
        return itemRepository.findItemDTOsByType(type);
    }

    public ItemDTO getItemById(Long id) {
        return itemRepository.findItemById(id);
    }
}
