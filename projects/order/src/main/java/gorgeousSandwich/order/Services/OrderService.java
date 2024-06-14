package gorgeousSandwich.order.Services;

import gorgeousSandwich.order.Domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private static final int MAX_PERCENTAGE = 100;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final IOrderMapper mapper;

    private final IOrderRepository repository;

    public OrderService(IOrderMapper mapper, IOrderRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public OrderDTO createOrder(CreateOrderDTO dto) {
        LOGGER.debug("Creating order...");
        Order order = mapper.CreateDTOtoDomain(dto, calculateFinalTotalPrice(dto));
        LOGGER.debug("Saving order onto database...");
        LOGGER.debug(String.format("Saving order %s to database", order));
        order = repository.save(order);
        if (order == null) {
            throw new RuntimeException("Could not save the entity to the database!");
        }
        LOGGER.info("Order successfully saved onto database");
        return mapper.toDTO(order);
    }

    @Override
    public Iterable<OrderDTO> getAll() {
        LOGGER.debug("Retrieving all orders from database...");
        List<Order> orders = repository.findAll();
        LOGGER.debug("Mapping orders to DTOs...");
        List<OrderDTO> dtos = new ArrayList<>();
        orders.forEach(o -> dtos.add(mapper.toDTO(o)));
        LOGGER.info("Successfully retrieved orders from database");
        return dtos;
    }

    private double calculateFinalTotalPrice(CreateOrderDTO order) {
        double totalPrice = calculateTotalPrice(order);
        final double initialPrice = totalPrice;
        totalPrice -= (order.promotion.getPromotion() / MAX_PERCENTAGE) * initialPrice;

        return totalPrice;
    }

    private double calculateTotalPrice(CreateOrderDTO order) {
        double totalPriceWithoutPromotion = 0;
        for (ProductEntryDTO dto : order.productEntries) {
            totalPriceWithoutPromotion += dto.quantity * dto.price;
        }

        return totalPriceWithoutPromotion;
    }
}
