package br.com.estampamente.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cardNumber;

    @Column
    private Integer cvv;

    @Column
    private String cardName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
