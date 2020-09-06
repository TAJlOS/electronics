package electronics.backend.repository;

import electronics.backend.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findAllByUserId(Long id);
}
