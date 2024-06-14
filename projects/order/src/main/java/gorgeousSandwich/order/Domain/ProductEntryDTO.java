package gorgeousSandwich.order.Domain;

public class ProductEntryDTO {

    public Long price;
    public int quantity;

    public ProductEntryDTO(Long price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
}
