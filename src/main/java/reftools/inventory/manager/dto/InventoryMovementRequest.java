package reftools.inventory.manager.dto;

import reftools.inventory.manager.model.MovementType;

public class InventoryMovementRequest {
    private Long autopartId;
    private Integer quantity;
    private MovementType type;
    private String reason;

    // Getters y Setters
    public Long getAutopartId() {
        return autopartId;
    }

    public void setAutopartId(Long autopartId) {
        this.autopartId = autopartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
