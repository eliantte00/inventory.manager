package reftools.inventory.manager.service;

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
}
