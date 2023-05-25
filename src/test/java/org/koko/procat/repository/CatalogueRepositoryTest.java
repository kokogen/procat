package org.koko.procat.repository;

import org.junit.jupiter.api.Test;
import org.koko.procat.entity.Catalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CatalogueRepositoryTest {

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Test
    public void saveOneCatalogue(){
        Catalogue catalogue1 = Catalogue.builder()
                .catalogueName("Laptops")
                .build();

        Catalogue catalogue2 = catalogueRepository.save(catalogue1);
        System.out.println(catalogue2);
    }

    @Test
    public void saveTwoCatalogues(){
        Catalogue catalogue1 = Catalogue.builder()
                .catalogueName("Monitors")
                .build();
        Catalogue catalogue2 = Catalogue.builder()
                .catalogueName("HDDs")
                .build();
        List<Catalogue> catalogues = catalogueRepository.saveAll(List.of(catalogue1, catalogue2));
        System.out.println(catalogues);
    }

    @Test
    public void updateCatalogue(){
        List<Catalogue> catalogues = catalogueRepository.findAll();
        Catalogue catalogue1 = catalogues.get(0);

        catalogue1.setCatalogueName(catalogue1.getCatalogueName() + "#next");
        Catalogue catalogue2 = catalogueRepository.save(catalogue1);
        System.out.println(catalogue2);
    }
}