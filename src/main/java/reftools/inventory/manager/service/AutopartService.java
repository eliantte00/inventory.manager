package reftools.inventory.manager.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reftools.inventory.manager.model.Autopart;
import reftools.inventory.manager.model.InventoryMovement;
import reftools.inventory.manager.model.MovementType;
import reftools.inventory.manager.repository.AutopartRepository;
import reftools.inventory.manager.repository.InventoryMovementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutopartService {

    private final AutopartRepository autopartRepository;
    private final InventoryMovementRepository movementRepository;

    public AutopartService(AutopartRepository autopartRepository, InventoryMovementRepository movementRepository) {
        this.autopartRepository = autopartRepository;
        this.movementRepository = movementRepository;
    }

    @Transactional
    public Autopart createAutopart(Autopart autopart) {
        Optional<Autopart> existing = autopartRepository.findBySku(autopart.getSku());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Ya existe una refacción registrada con ese SKU.");
        }
        return autopartRepository.save(autopart);
    }

    @Transactional(readOnly = true)
    public List<Autopart> getAllAutoparts() {
        return autopartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autopart> searchAutoparts(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        String sanitized = query.trim();
        return autopartRepository
                .findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrSkuContainingIgnoreCase(
                        sanitized,
                        sanitized,
                        sanitized
                );
    }

    @Transactional(readOnly = true)
    public Autopart getAutopart(Long id) {
        return autopartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Refacción no encontrada"));
    }

    @Transactional
    public Autopart updateAutopart(Long id, Autopart data) {
        Autopart existing = getAutopart(id);
        Optional<Autopart> conflict = autopartRepository.findBySku(data.getSku());
        if (conflict.isPresent() && !conflict.get().getId().equals(id)) {
            throw new IllegalArgumentException("Ya existe una refacción con ese SKU.");
        }
        existing.setName(data.getName());
        existing.setBrand(data.getBrand());
        existing.setSku(data.getSku());
        existing.setPrice(data.getPrice());
        existing.setAmount(data.getAmount());
        return autopartRepository.save(existing);
    }

    @Transactional
    public void deleteAutopart(Long id) {
        if (!autopartRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró la refacción con ID: " + id);
        }
        autopartRepository.deleteById(id);
    }

    @Transactional
    public Autopart addStock(Long id, Integer quantity) {
        return updateStock(id, quantity, MovementType.ENTRADA, "Ajuste manual de inventario");
    }

    @Transactional
    public Autopart updateStock(Long id, Integer quantity, MovementType type, String reason) {
        Autopart autopart = getAutopart(id);
        
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        // Actualizar la cantidad en el inventario
        int newAmount = autopart.getAmount();
        if (type == MovementType.ENTRADA) {
            newAmount += quantity;
        } else {
            if (autopart.getAmount() < quantity) {
                throw new IllegalArgumentException("No hay suficientes existencias para realizar esta operación");
            }
            newAmount -= quantity;
        }

        // Guardar el movimiento de inventario
        InventoryMovement movement = new InventoryMovement(autopart, quantity, type, reason);
        movementRepository.save(movement);

        // Actualizar la cantidad en la refacción
        autopart.setAmount(newAmount);
        return autopartRepository.save(autopart);
    }

    @Transactional(readOnly = true)
    public List<InventoryMovement> getMovementHistory(Long autopartId) {
        return movementRepository.findByAutopartIdOrderByMovementDateDesc(autopartId);
    }
}
