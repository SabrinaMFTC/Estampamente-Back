package br.com.estampamente.entity;

import br.com.estampamente.entity.enums.ItemType;
import br.com.estampamente.entity.enums.Size;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String description;

    @Column
    private Integer discount;

    @Column
    private String brand;

    @Column
    private Integer quantity;

    @Column
    private String imageLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private Size size;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();
}
