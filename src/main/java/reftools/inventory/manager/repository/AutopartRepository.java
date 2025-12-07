package reftools.inventory.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reftools.inventory.manager.model.Autopart;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutopartRepository extends JpaRepository<Autopart, Long> {

    Optional<Autopart> findBySku(String sku);

    List<Autopart> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrSkuContainingIgnoreCase(String name,
                                                                                                     String brand,
                                                                                                     String sku);
}
