package gorgeousSandwich.order.Controllers;

import gorgeousSandwich.order.Domain.CreateOrderDTO;
import gorgeousSandwich.order.Domain.IOrderService;
import gorgeousSandwich.order.Domain.OrderDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final IOrderService service;

    @MutationMapping
    public ResponseEntity<OrderDTO> createOrder(@Argument CreateOrderDTO orderDTO) {
        LOGGER.trace(String.format("Requesting the creation of a new order (%s)", orderDTO));
        try {
            OrderDTO dto = service.createOrder(orderDTO);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            LOGGER.error("Could not create Order!", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @QueryMapping
    public List<OrderDTO> listAllOrders() {
        Iterable<OrderDTO> itr = service.getAll();
        List<OrderDTO> l = new ArrayList<>((Collection<? extends OrderDTO>) itr);
        return l;
    }
}
