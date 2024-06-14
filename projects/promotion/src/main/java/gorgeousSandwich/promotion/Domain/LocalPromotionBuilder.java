package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.domain.valueobjects.Percentage;
import gorgeousSandwich.promotion.Shared.domain.valueobjects.TimeOfEffect;
import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LocalPromotionBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalPromotionBuilder.class);
    private Long id;
    private double percentage;
    private Date from;
    private Date to;

    private Long shop;

    public LocalPromotionBuilder() {
        id=null;
        percentage=-1;
        from=null;
        to=null;
        this.shop=null;
    }



    public LocalPromotionBuilder withId(Long id){
        this.id=id;
        return this;
    }

    public LocalPromotionBuilder withPercentage(double percentage){
        this.percentage=percentage;
        return this;
    }

    public LocalPromotionBuilder withFrom(Date from){
        this.from=from;
        return this;
    }

    public LocalPromotionBuilder withTo(Date to){
        this.to=to;
        return this;
    }

    public LocalPromotionBuilder withShop(Long shop){
        this.shop=shop;
        return this;
    }

    public LocalPromotion build() throws BusinessRuleViolationException {
        try {
            if (id==null){
                return new LocalPromotion(TimeOfEffect.of(from,to), Percentage.of(percentage),shop);
            }
            return new LocalPromotion(id, TimeOfEffect.of(from,to), Percentage.of(percentage),shop);
        } catch (BusinessRuleViolationException e) {
            LOGGER.error("Could not local promotion with the information given!");
            throw new BusinessRuleViolationException("Could not local promotion with the information given!",e);
        }
    }
}
