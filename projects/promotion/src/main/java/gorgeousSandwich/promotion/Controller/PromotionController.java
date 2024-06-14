package gorgeousSandwich.promotion.Controller;

import gorgeousSandwich.promotion.Domain.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class PromotionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionController.class);
    private final IPromotionService service;
    private final IPromotionMapper mapper;


    @MutationMapping
    public GraphQLPromotionDTO createPromotion(@Argument PromotionInput promotion) {
        try {
            PromotionDTO promotionDTO = promotion.toDTO();
            LOGGER.trace(String.format("Requesting the creation of a new promotion (%s)", promotionDTO));
            promotionDTO = service.createPromotion(promotionDTO);
            LOGGER.info("Order successfully created");
            return mapper.toGraphQLDTO(promotionDTO);
        } catch (Exception e) {
            LOGGER.error("Could not create Shop!", e);
            return null;
        }
    }

    @QueryMapping
    public List<GraphQLPromotionDTO> listAllPromotions() {
        Iterable<PromotionDTO> itr = service.getAll();
        List<GraphQLPromotionDTO> l = new ArrayList<>();
        itr.forEach(dto -> l.add(mapper.toGraphQLDTO(dto)));
        LOGGER.info("Retrieving all promotions");
        return l;
    }

    @QueryMapping
    public String health() {
        return "All systems Online!";
    }

    @QueryMapping
    public GraphQLPromotionDTO listPromotion(@Argument Long id){
        Optional<PromotionDTO> opt = service.getPromotion(id);
        if (opt.isPresent()){
            return opt.map(mapper::toGraphQLDTO).get();
        }
        return null;
    }


    record PromotionInput(Float percentage, String from, String to, String shopId, PromotionType promotionType) {
        PromotionDTO toDTO() throws ParseException {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return new PromotionDTO(null, percentage, format.parse(from), format.parse(to), shopId!=null ? Long.parseLong(shopId) : null, promotionType);
        }
    }
}
