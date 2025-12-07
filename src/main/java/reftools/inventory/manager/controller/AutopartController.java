package reftools.inventory.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reftools.inventory.manager.dto.AutopartRequest;
import reftools.inventory.manager.model.Autopart;
import reftools.inventory.manager.service.AutopartService;

@RestController
@RequestMapping("/api/autoparts")
public class AutopartController {

    private final AutopartService autopartService;

    public AutopartController(AutopartService autopartService) {
        this.autopartService = autopartService;
    }

    @PostMapping
    public ResponseEntity<Autopart> create(@RequestBody AutopartRequest request) {
        Autopart autopart = new Autopart(
                request.getName(),
                request.getBrand(),
                request.getSku(),
                request.getPrice(),
                request.getAmount()
        );
        Autopart saved = autopartService.createAutopart(autopart);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
