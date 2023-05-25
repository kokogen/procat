package org.koko.procat.repository;

import org.koko.procat.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //@Query("select distinct p from Product p left join fetch p.catalogues")
    @EntityGraph(value = "Product.catalogues")
    List<Product> findAll();

    //@Query("select distinct p from Product p left join fetch p.catalogues where p.id = :id")
    //@EntityGraph(value = "Product.catalogues")
    Optional<Product> findById(Long id);

}
