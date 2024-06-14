package gorgeousSandwich.promotion.Service;

import gorgeousSandwich.promotion.Domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionService.class);
    private IPromotionMapper mapper;
    private IPromotionRepository repository;

    public PromotionService(IPromotionMapper mapper, IPromotionRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public PromotionDTO createPromotion(PromotionDTO dto) {
        LOGGER.debug("Creating promotion...");
        Promotion promotion = mapper.toDomain(dto);
        LOGGER.debug(String.format("Saving promotion %s to the database", promotion));
        promotion = repository.save(promotion);
        if (promotion == null) {
            LOGGER.error("Could not save the entity to the database!");
            throw new RuntimeException("Could not save the entity to the database!");
        }
        LOGGER.info("Successfully saved promotion onto database");
        return mapper.toDTO(promotion);
    }

    @Override
    public Iterable<PromotionDTO> getAll() {
        LOGGER.debug("Retrieving all promotions...");
        List<Promotion> promotions = repository.findAll();
        List<PromotionDTO> dtos = new ArrayList<>();
        LOGGER.debug("Mapping promotions to DTOs");
        promotions.forEach(p -> dtos.add(mapper.toDTO(p)));
        LOGGER.info("Successfully retrieved all promotions from database");
        return dtos;
    }

    @Override
    public Optional<PromotionDTO> getPromotion(Long id) {
        Optional<Promotion> opt = repository.findById(id);
        if (opt.isPresent()) {
            return opt.map(mapper::toDTO);
        }
        return Optional.empty();
    }
}
