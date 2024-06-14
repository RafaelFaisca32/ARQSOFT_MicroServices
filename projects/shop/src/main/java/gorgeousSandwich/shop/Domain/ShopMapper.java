package gorgeousSandwich.shop.Domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import gorgeousSandwich.shop.Shared.exceptions.BusinessRuleViolationException;

@Component
public class ShopMapper implements IShopMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopMapper.class);

    @Override
    public Shop toDomain(ShopDTO dto) {
        ShopBuilder shopBuilder = new ShopBuilder();
        try {
            if (dto.id != null) {
                shopBuilder.withId(dto.id);
            }
            return shopBuilder.withName(dto.name).withMondayOpening(dto.mondayOpening).withMondayClosing(dto.mondayClosing).
                    withTuesdayOpening(dto.tuesdayOpening).withTuesdayClosing(dto.tuesdayClosing).withWednesdayOpening(dto.wednesdayOpening).
                    withWednesdayClosing(dto.wednesdayClosing).withThursdayOpening(dto.thursdayOpening).
                    withThursdayClosing(dto.thursdayClosing).withFridayOpening(dto.fridayOpening).withFridayClosing(dto.fridayClosing).
                    withSaturdayOpening(dto.saturdayOpening).withSaturdayClosing(dto.saturdayClosing).withSundayOpening(dto.sundayOpening).
                    withSundayClosing(dto.sundayClosing).withManagerName(dto.managerName).withManagerId(dto.managerId).build();
        } catch (BusinessRuleViolationException e) {
            LOGGER.error(String.format("Information on Shop is invalid (%s)!", dto), e);
            throw new RuntimeException("Information on Shop is invalid!", e);
        }
    }

    @Override
    public ShopDTO toDTO(Shop shop) {
        return new ShopDTO(shop.getMonday().getSchedule().getOpeningHour(), shop.getMonday().getSchedule().getOpeningHour(),
                shop.getTuesday().getSchedule().getOpeningHour(), shop.getTuesday().getSchedule().getOpeningHour(),
                shop.getWednesday().getSchedule().getOpeningHour(), shop.getWednesday().getSchedule().getOpeningHour(),
                shop.getThursday().getSchedule().getOpeningHour(), shop.getThursday().getSchedule().getOpeningHour(),
                shop.getFriday().getSchedule().getOpeningHour(), shop.getFriday().getSchedule().getOpeningHour(),
                shop.getSaturday().getSchedule().getOpeningHour(), shop.getSaturday().getSchedule().getOpeningHour(),
                shop.getSunday().getSchedule().getOpeningHour(), shop.getSunday().getSchedule().getOpeningHour(),
                shop.getName().getStringValue(), shop.getId(), shop.getManager().getManagerName(), shop.getManager().getManagerId());
    }
}
