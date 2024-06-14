package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.domain.valueobjects.Percentage;
import gorgeousSandwich.promotion.Shared.domain.valueobjects.TimeOfEffect;
import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class GlobalPromotionBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalPromotionBuilder.class);
    private Long id;
    private double percentage;
    private Date from;
    private Date to;

    public GlobalPromotionBuilder() {
        id=null;
        percentage=-1;
        from=null;
        to=null;
    }



    public GlobalPromotionBuilder withId(Long id){
        this.id=id;
        return this;
    }

    public GlobalPromotionBuilder withPercentage(double percentage){
        this.percentage=percentage;
        return this;
    }

    public GlobalPromotionBuilder withFrom(Date from){
        this.from=from;
        return this;
    }

    public GlobalPromotionBuilder withTo(Date to){
        this.to=to;
        return this;
    }

    public GlobalPromotion build() throws BusinessRuleViolationException {
        try {
            if (id==null){
                return new GlobalPromotion(TimeOfEffect.of(from,to), Percentage.of(percentage));
            }
            return new GlobalPromotion(id, TimeOfEffect.of(from,to), Percentage.of(percentage));
        } catch (BusinessRuleViolationException e) {
            LOGGER.error("Could not local promotion with the information given!");
            throw new BusinessRuleViolationException("Could not local promotion with the information given!",e);
        }
    }


}
