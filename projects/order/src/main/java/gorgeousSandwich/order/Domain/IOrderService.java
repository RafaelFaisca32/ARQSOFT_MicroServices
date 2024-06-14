package gorgeousSandwich.order.Domain;

public interface IOrderService {

    OrderDTO createOrder(CreateOrderDTO order);

    Iterable<OrderDTO> getAll();
}
