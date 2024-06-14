package gorgeousSandwich.order.Domain;

import java.util.List;

public class OrderDTO {

    public Long id;
    public Long shopId;
    public List<ProductEntryDTO> productEntry;
    public double totalPrice;

    public OrderDTO(Long id, Long shopId, List<ProductEntryDTO> productEntry, double totalPrice) {
        this.id = id;
        this.shopId = shopId;
        this.productEntry = productEntry;
        this.totalPrice = totalPrice;
    }
}
