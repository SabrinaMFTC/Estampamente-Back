package br.com.estampamente.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;

    @Column
    private Integer number;

    @Column
    private String zipCode;

    @Column
    private String city;

    @Column
    private String country;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
