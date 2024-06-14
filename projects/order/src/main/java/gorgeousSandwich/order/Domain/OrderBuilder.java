package gorgeousSandwich.order.Domain;

import gorgeousSandwich.order.Shared.domain.valueobjects.Promotion;
import gorgeousSandwich.order.Shared.domain.valueobjects.ShopId;
import gorgeousSandwich.order.Shared.domain.valueobjects.TotalPrice;
import gorgeousSandwich.order.Shared.exceptions.BusinessRuleViolationException;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    public Long id;

    public Long shopId;

    public List<ProductEntryDTO> productEntries;

    public Promotion promotion;

    public double totalPrice;

    public OrderBuilder() {
        this.id = null;
        this.shopId = null;
        this.productEntries = null;
        this.promotion = null;
        this.totalPrice = 6.7;
    }

    public OrderBuilder withShopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public OrderBuilder withProductEntry(List<ProductEntryDTO> productEntries) {
        this.productEntries = productEntries;
        return this;
    }

    public OrderBuilder withPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }

    public OrderBuilder withTotalPrice(double price) {
        this.totalPrice = price;
        return this;
    }

    public Order build() throws BusinessRuleViolationException {
        List<ProductEntry> pe = new ArrayList<>();
        for (ProductEntryDTO productEntryDTO : productEntries) {
            pe.add(new ProductEntry(productEntryDTO.price, productEntryDTO.quantity));
        }
        return new Order(id, shopId, pe, promotion, new TotalPrice(totalPrice));
    }
}
