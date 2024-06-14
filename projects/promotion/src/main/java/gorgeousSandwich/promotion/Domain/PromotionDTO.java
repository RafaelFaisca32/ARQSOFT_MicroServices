package gorgeousSandwich.promotion.Domain;

import java.util.Date;

public class PromotionDTO {
    public Long id;

    public double percentage;

    public Date from;

    public Date to;

    public Long shopId;

    public PromotionType promotionType;


    public PromotionDTO(Long id, double percentage, Date from, Date to, Long shopId, PromotionType promotionType) {
        this.id = id;
        this.percentage = percentage;
        this.from = from;
        this.to = to;
        this.shopId = shopId;
        this.promotionType=promotionType;
    }
}
