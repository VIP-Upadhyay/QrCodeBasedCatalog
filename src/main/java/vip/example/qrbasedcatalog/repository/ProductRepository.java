package vip.example.qrbasedcatalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vip.example.qrbasedcatalog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String Id);
    Page<Product> findByProductId(Long id, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    @Modifying
    @Query("delete from Product p where p.name = ?1")
    void deleteByName(String name);

}
