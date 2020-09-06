package electronics.backend.dto.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailRequestData implements Serializable {
    private Long id;
    private Integer cnt;
}
