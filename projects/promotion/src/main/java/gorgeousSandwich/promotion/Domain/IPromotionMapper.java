package gorgeousSandwich.promotion.Domain;


import java.text.ParseException;

public interface IPromotionMapper {

    Promotion toDomain(PromotionDTO dto);

    PromotionDTO toDTO(Promotion domain);

    GraphQLPromotionDTO toGraphQLDTO(PromotionDTO dto);


}
