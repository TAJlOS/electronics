package electronics.backend.repository;

import electronics.backend.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;


public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
