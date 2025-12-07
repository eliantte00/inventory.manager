package reftools.inventory.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reftools.inventory.manager.dto.AutopartRequest;
import reftools.inventory.manager.mapper.AutopartMapper;
import reftools.inventory.manager.model.Autopart;
import reftools.inventory.manager.service.AutopartService;

@RestController
@RequestMapping("/api/autoparts")
public class AutopartController {

    private final AutopartService autopartService;

    public AutopartController(AutopartService autopartService) {
        this.autopartService = autopartService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(autopartService.getAllAutoparts());
    }

    @PostMapping
    public ResponseEntity<Autopart> create(@RequestBody AutopartRequest request) {
        Autopart saved = autopartService.createAutopart(AutopartMapper.fromRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AutopartRequest request) {
        try {
            Autopart updated = autopartService.updateAutopart(id, AutopartMapper.fromRequest(request));
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            autopartService.deleteAutopart(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
