package org.koko.procat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "manufacturer")
@EqualsAndHashCode(exclude = "products")
@NamedEntityGraph(
        name = "Manufacturer.products",
        attributeNodes = @NamedAttributeNode(value = "products", subgraph = "product-catalogues"),
        subgraphs = @NamedSubgraph(name = "product-catalogues", attributeNodes = @NamedAttributeNode("catalogues"))
)
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manufacturer")
    @SequenceGenerator(
            name = "seq_manufacturer",
            sequenceName = "seq_manufacturer",
            initialValue = 1
    )
    private Long manufacturerId;

    private String manufacturerName;

    private String countryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}
