package reftools.inventory.manager.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reftools.inventory.manager.model.Autopart;
import reftools.inventory.manager.repository.AutopartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutopartService {

    private final AutopartRepository autopartRepository;

    public AutopartService(AutopartRepository autopartRepository) {
        this.autopartRepository = autopartRepository;
    }

    @Transactional
    public Autopart createAutopart(Autopart autopart) {
        Optional<Autopart> existing = autopartRepository.findBySku(autopart.getSku());
        if (existing.isPresent()) {
            Autopart persisted = existing.get();
            persisted.setName(autopart.getName());
            persisted.setBrand(autopart.getBrand());
            persisted.setPrice(autopart.getPrice());
            persisted.setAmount(persisted.getAmount() + autopart.getAmount());
            return autopartRepository.save(persisted);
        }
        return autopartRepository.save(autopart);
    }

    @Transactional(readOnly = true)
    public List<Autopart> getAllAutoparts() {
        return autopartRepository.findAll();
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
            throw new EntityNotFoundException("No se encontró la refacción a eliminar");
        }
        autopartRepository.deleteById(id);
    }
}
