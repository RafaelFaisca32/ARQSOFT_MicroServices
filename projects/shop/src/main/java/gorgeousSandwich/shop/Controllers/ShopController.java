package gorgeousSandwich.shop.Controllers;

import gorgeousSandwich.shop.Domain.CreateShopDTO;
import gorgeousSandwich.shop.Domain.IShopService;
import gorgeousSandwich.shop.Domain.ShopDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ShopController{

    private final IShopService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

    @MutationMapping
    public ShopDTO createShop(@Argument ShopInput shop) {
        try {
            ShopDTO dto = new CreateShopDTO(shop.mondayOpening, shop.mondayClosing, shop.tuesdayOpening, shop.tuesdayClosing, shop.wednesdayOpening,
                    shop.wednesdayClosing, shop.thursdayOpening, shop.thursdayClosing, shop.fridayOpening, shop.fridayClosing, shop.saturdayOpening,
                    shop.saturdayClosing, shop.sundayOpening, shop.sundayClosing, shop.name, shop.managerName, shop.managerId);
            LOGGER.debug(String.format("Requesting the creation of a new shop (%s)", dto.toString()));


            dto = service.createShop(dto);
            LOGGER.info("Shop was successfully created");
            return dto;
        } catch (Exception e) {
            LOGGER.error("Could not create Shop!", e);
            return null;
        }
    }

    @QueryMapping
    public List<ShopDTO> listAllShops() {
        Iterable<ShopDTO> itr = service.getAll();
        List<ShopDTO> l = new ArrayList<>((Collection<? extends ShopDTO>) itr);
        LOGGER.info("Retrieving all registered shops");
        return l;
    }

    @QueryMapping
    public ShopDTO listShop(@Argument Long id) {
        Optional<ShopDTO> shop = service.getShop(id);
        return shop.orElseGet(() -> null);
    }

    record ShopInput(Integer mondayOpening,
                     Integer mondayClosing,
                     Integer tuesdayOpening,
                     Integer tuesdayClosing,
                     Integer wednesdayOpening,
                     Integer wednesdayClosing,
                     Integer thursdayOpening,
                     Integer thursdayClosing,
                     Integer fridayOpening,
                     Integer fridayClosing,
                     Integer saturdayOpening,
                     Integer saturdayClosing,
                     Integer sundayOpening,
                     Integer sundayClosing,
                     String name,
                     String managerName,
                     Long managerId) {
    }
}
