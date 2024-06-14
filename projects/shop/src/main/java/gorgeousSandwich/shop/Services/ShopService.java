package gorgeousSandwich.shop.Services;

import gorgeousSandwich.shop.Domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService implements IShopService {
    private final IShopMapper mapper;

    private final IShopRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopService.class);

    public ShopService(IShopMapper mapper, IShopRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public Iterable<ShopDTO> getAll() {
        LOGGER.debug("Retrieving all shops from database...");
        List<Shop> shops = this.repository.findAll();
        List<ShopDTO> dtos = new ArrayList<>();
        LOGGER.debug("Mapping shops to DTOs");
        shops.forEach(s -> dtos.add(mapper.toDTO(s)));
        LOGGER.info("Successfully retrieved shops from database");
        return dtos;
    }

    @Override
    public ShopDTO createShop(ShopDTO dto) {
        LOGGER.debug("Creating shop...");
        LOGGER.debug("Validating user...");
        LOGGER.debug("Mapping DTO to shop...");
        Shop shop = mapper.toDomain(dto);
        LOGGER.debug("Saving shop onto the database...");
        shop = repository.save(shop);
        if (shop == null) {
            LOGGER.error("Could not save the entity to the database!");
            throw new RuntimeException("Could not save the entity to the database!");
        }
        LOGGER.info("Successfully saved shop onto database");
        return mapper.toDTO(shop);
    }

    @Override
    public Optional<ShopDTO> getShop(Long id) {
        try {
            Shop s = repository.getReferenceById(id);
            return Optional.of(mapper.toDTO(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
