package gorgeousSandwich.shop.Domain;

public interface IShopMapper {
    Shop toDomain(ShopDTO dto);
    ShopDTO toDTO(Shop shop);
}
