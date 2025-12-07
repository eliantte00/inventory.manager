package reftools.inventory.manager.mapper;

import reftools.inventory.manager.dto.AutopartRequest;
import reftools.inventory.manager.model.Autopart;

public final class AutopartMapper {

    private AutopartMapper() {
    }

    public static Autopart fromRequest(AutopartRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Autopart request must not be null");
        }
        return new Autopart(
                request.getName(),
                request.getBrand(),
                request.getSku(),
                request.getPrice(),
                request.getAmount()
        );
    }
}
