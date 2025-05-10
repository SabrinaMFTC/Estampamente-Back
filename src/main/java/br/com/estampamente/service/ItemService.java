package br.com.estampamente.service;

import br.com.estampamente.entity.DTOs.ItemDTO;
import br.com.estampamente.entity.Item;
import br.com.estampamente.entity.enums.ItemType;
import br.com.estampamente.repository.ItemRepository;
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
        return itemRepository.findById(id);
    }
}
