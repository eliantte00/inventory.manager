package reftools.inventory.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reftools.inventory.manager.model.InventoryMovement;

import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    List<InventoryMovement> findByAutopartIdOrderByMovementDateDesc(Long autopartId);
}
