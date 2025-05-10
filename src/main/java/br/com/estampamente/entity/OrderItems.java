package br.com.estampamente.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items") // <- nome mais seguro e compatível com PostgreSQL
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer itemQuantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
