package gorgeousSandwich.promotion.Domain;

import java.util.Optional;

public interface IPromotionService {

    PromotionDTO createPromotion(PromotionDTO promotion);

    Iterable<PromotionDTO> getAll();

    Optional<PromotionDTO> getPromotion(Long id);


}
