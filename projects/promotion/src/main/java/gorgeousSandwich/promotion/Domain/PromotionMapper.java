package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PromotionMapper implements IPromotionMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionMapper.class);

    @Override
    public Promotion toDomain(PromotionDTO dto) {
        try {
            switch (dto.promotionType) {
                case LOCAL:
                    return new LocalPromotionBuilder().withFrom(dto.from).withTo(dto.to).withPercentage(dto.percentage)
                            .withShop(dto.shopId).withId(dto.id).build();
                case GLOBAL:
                default:
                    return new GlobalPromotionBuilder().withPercentage(dto.percentage).withId(dto.id)
                            .withFrom(dto.from).withTo(dto.to).build();
            }
        } catch (BusinessRuleViolationException e) {
            LOGGER.error("Cannot convert DTO to Domain!", e);
            throw new RuntimeException("Cannot convert DTO to Domain!", e);
        }
    }

    @Override
    public PromotionDTO toDTO(Promotion domain) {
        if (!(domain instanceof LocalPromotion)) {
            return new PromotionDTO(domain.getId(), domain.getPercentage().getPercentage(), domain.getTimeOfEffect().getFrom(), domain.getTimeOfEffect().getTo(), null, domain.getType());
        }
        return new PromotionDTO(domain.getId(), domain.getPercentage().getPercentage(), domain.getTimeOfEffect().getFrom(), domain.getTimeOfEffect().getTo(), ((LocalPromotion) domain).getShopId(), domain.getType());

    }

    @Override
    public GraphQLPromotionDTO toGraphQLDTO(PromotionDTO dto) {
        String shopId=null;
        if (dto.shopId!=null){
            shopId=Long.toString(dto.shopId);
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return new GraphQLPromotionDTO(Long.toString(dto.id), Float.parseFloat(Double.toString(dto.percentage)), format.format(dto.from),
                format.format(dto.to),shopId, dto.promotionType);
    }
}
