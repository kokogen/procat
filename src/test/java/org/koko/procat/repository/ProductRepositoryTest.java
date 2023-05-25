package org.koko.procat.repository;

import org.junit.jupiter.api.Test;
import org.koko.procat.entity.Catalogue;
import org.koko.procat.entity.Manufacturer;
import org.koko.procat.entity.Product;
import org.koko.procat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    public void saveProduct(){
        Manufacturer manufacturer = Manufacturer.builder()
                .countryName("South Korea")
                .manufacturerName("Samsung")
                .build();

        Catalogue catalogue_n = Catalogue.builder()
                .catalogueName("VideoCards")
                .build();

        Catalogue catalogue1 = catalogueRepository.save(catalogue_n);

        Optional<Catalogue> catalogue2 = catalogueRepository.findById(3L);

        Product product = Product.builder()
                .productName("Samsung Raptor")
                .manufacturer(manufacturer)
                .catalogues(List.of(catalogue1, catalogue2.get()))
                .build();

        Product product1 = productRepository.save(product);
    }

    @Test
    public void selectProducts(){
        List<Product> products = productService.findAll();
        System.out.println("products = " + products);
        System.out.println("manufacturer = " + products.get(0).getManufacturer());
    }

    @Test
    public void selectManufacturers(){
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        System.out.println("manufacturers = " + manufacturers);
        manufacturers.get(0).getProducts().forEach(x -> System.out.println(x.getManufacturer().getManufacturerName()));
    }
}