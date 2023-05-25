package org.koko.procat.repository;

import org.koko.procat.entity.Manufacturer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    //@Query("select distinct m from Manufacturer m left join fetch m.products")
    @EntityGraph(value = "Manufacturer.products")
    List<Manufacturer> findAll();
}
