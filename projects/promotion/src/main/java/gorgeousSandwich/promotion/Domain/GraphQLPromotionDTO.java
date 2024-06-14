package gorgeousSandwich.promotion.Domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class GraphQLPromotionDTO {
    public String id;
    public float percentage;
    public String from;
    public String to;
    public String shopId;
    public PromotionType promotionType;
}
