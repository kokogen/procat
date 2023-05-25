package org.koko.procat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "catalogue",
        uniqueConstraints = @UniqueConstraint(name = "catalogue_name_uniq", columnNames = "catalogue_name")
)
public class Catalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_catalogue")
    @SequenceGenerator(
            name = "seq_catalogue",
            sequenceName = "seq_catalogue",
            initialValue = 1
    )
    private Long catalogueId;

    @Column(name = "catalogue_name", nullable = false)
    private String catalogueName;
}
