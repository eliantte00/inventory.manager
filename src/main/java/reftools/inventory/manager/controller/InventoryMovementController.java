package reftools.inventory.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reftools.inventory.manager.dto.InventoryMovementRequest;
import reftools.inventory.manager.model.InventoryMovement;
import reftools.inventory.manager.model.MovementType;
import reftools.inventory.manager.service.AutopartService;

import java.util.List;

@RestController
@RequestMapping("/api/autoparts/{id}/movements")
public class InventoryMovementController {

    private final AutopartService autopartService;

    public InventoryMovementController(AutopartService autopartService) {
        this.autopartService = autopartService;
    }

    @PostMapping
    public ResponseEntity<?> registerMovement(
            @PathVariable Long id, 
            @RequestBody InventoryMovementRequest request) {
        try {
            // Si no se proporciona una razón, establecer un mensaje predeterminado
            String reason = request.getReason();
            if (reason == null || reason.trim().isEmpty()) {
                reason = request.getType() == MovementType.ENTRADA ? 
                    "Entrada de inventario" : "Salida de inventario";
            }
            
            autopartService.updateStock(
                id, 
                request.getQuantity(), 
                request.getType(), 
                reason
            );
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<InventoryMovement>> getMovementHistory(@PathVariable Long id) {
        List<InventoryMovement> history = autopartService.getMovementHistory(id);
        return ResponseEntity.ok(history);
    }
}
