package electronics.backend.dto.order;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestData implements Serializable {
    private Double sum;
    private List<OrderDetailRequestData> details = new ArrayList<>();

}
