package electronics.backend.dto.order;

import electronics.backend.entity.CPU;
import lombok.Data;

@Data
public class UserOrderDetails {
    public UserOrderDetails(CPU cpu, Integer amount) {
        this.id = cpu.getId();
        this.name = cpu.getName();
        this.img = cpu.getImg();
        this.price = cpu.getPrice();
        this.amount = amount;
    }

    private Long id;
    private String name;
    private String img;
    private Double price;
    private Integer amount;
}
