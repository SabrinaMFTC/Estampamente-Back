package br.com.estampamente.repositories;

import br.com.estampamente.entities.DTOs.ItemDTO;
import br.com.estampamente.entities.Item;
import br.com.estampamente.entities.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("""
        SELECT new br.com.estampamente.entities.DTOs.ItemDTO(
            i.id,
            i.name,
            i.description,
            i.price,
            i.itemType,
            i.discount,
            i.brand,
            i.imageLink
        )
        FROM Item i
        WHERE i.itemType = :type
    """)
    List<ItemDTO> findItemDTOsByType(@Param("type") ItemType type);

    @Query("""
    SELECT new br.com.estampamente.entities.DTOs.ItemDTO(
        i.id,
        i.name,
        i.description,
        i.price,
         i.itemType,
        i.discount,
        i.brand,
        i.imageLink
    )
    FROM Item i
    WHERE i.id = :id
""")
    ItemDTO findItemById(@Param("id") Long id);



}
