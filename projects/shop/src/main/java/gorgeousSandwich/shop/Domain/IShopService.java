package gorgeousSandwich.shop.Domain;

import java.util.Optional;

public interface IShopService {

    Iterable<ShopDTO> getAll();

    ShopDTO createShop(ShopDTO dto);

    Optional<ShopDTO> getShop(Long id);


}
