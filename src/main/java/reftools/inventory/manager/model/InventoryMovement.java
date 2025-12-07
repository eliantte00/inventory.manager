package reftools.inventory.manager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autopart_id", nullable = false)
    private Autopart autopart;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime movementDate;

    public InventoryMovement() {
        this.movementDate = LocalDateTime.now();
    }

    public InventoryMovement(Autopart autopart, Integer quantity, MovementType type, String reason) {
        this();
        this.autopart = autopart;
        this.quantity = quantity;
        this.type = type;
        this.reason = reason;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autopart getAutopart() {
        return autopart;
    }

    public void setAutopart(Autopart autopart) {
        this.autopart = autopart;
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

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }
}
