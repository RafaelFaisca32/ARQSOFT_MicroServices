package gorgeousSandwich.order.Domain;

import gorgeousSandwich.order.Shared.domain.valueobjects.Promotion;

import java.util.List;

public class CreateOrderDTO {

    public final Long shopId;

    public final List<ProductEntryDTO> productEntries;

    public final Promotion promotion;

    public CreateOrderDTO(Long shopId, List<ProductEntryDTO> productEntries, Promotion promotion) {
        this.shopId = shopId;
        this.productEntries = productEntries;
        this.promotion = promotion;
    }


}
