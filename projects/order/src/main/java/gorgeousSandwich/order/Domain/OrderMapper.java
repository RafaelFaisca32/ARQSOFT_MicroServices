package gorgeousSandwich.order.Domain;

import gorgeousSandwich.order.Shared.exceptions.BusinessRuleViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper implements IOrderMapper {

    @Override
    public Order CreateDTOtoDomain(CreateOrderDTO orderDTO, double price) {
        try {
            return new OrderBuilder()
                    .withShopId(orderDTO.shopId)
                    .withProductEntry(orderDTO.productEntries)
                    .withPromotion(orderDTO.promotion)
                    .withTotalPrice(price)
                    .build();
        } catch (BusinessRuleViolationException e) {
            throw new RuntimeException("Cannot convert DTO to Domain!", e);
        }
    }

    @Override
    public OrderDTO toDTO(Order domain) {
        List<ProductEntryDTO> productEntryDTOs = new ArrayList<>();
        for (ProductEntry pe: domain.getProductEntries()) {
            productEntryDTOs.add(new ProductEntryDTO(pe.getSandwichPrice(), pe.getQuantity().getValue()));
        }
        return new OrderDTO(domain.getId(), domain.getShopid(),
                productEntryDTOs, domain.getPrice().getTotalValue());
    }
}
