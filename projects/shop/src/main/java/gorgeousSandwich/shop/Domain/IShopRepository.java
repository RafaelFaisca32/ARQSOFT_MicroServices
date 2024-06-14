package gorgeousSandwich.shop.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAll();


}
