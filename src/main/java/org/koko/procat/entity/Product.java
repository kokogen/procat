package org.koko.procat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "manufacturer")
@EqualsAndHashCode(exclude = "catalogues")
@Table(
        name = "product"
)
@NamedEntityGraph(
        name = "Product.catalogues",
        attributeNodes = {
                @NamedAttributeNode(value = "catalogues"),
                @NamedAttributeNode(value = "manufacturer", subgraph = "manufacturer-products")
        },
        subgraphs = @NamedSubgraph(name = "manufacturer-products", attributeNodes = @NamedAttributeNode("products"))
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
    @SequenceGenerator(
            name = "seq_product",
            sequenceName = "seq_product",
            initialValue = 1
    )
    private Long productId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturerId")
    private Manufacturer manufacturer;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_catalogue",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(name = "catalogue_id", referencedColumnName = "catalogueId")
    )
    List<Catalogue> catalogues;
}
