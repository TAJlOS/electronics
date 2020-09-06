package electronics.backend.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import electronics.backend.entity.Order;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserOrdersResponse implements Serializable {
    public UserOrdersResponse(Order order) {
        this.id = order.getId();
        this.sum = order.getSum();
        this.status = order.getStatus();
        this.registration = order.getRegistration();
        this.products = order.getDetails().stream()
                .map(it -> new UserOrderDetails(it.getCpu(), it.getAmount()))
                .collect(Collectors.toList());
    }

    private Long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<UserOrderDetails> products;
    private Double sum;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registration;
}
