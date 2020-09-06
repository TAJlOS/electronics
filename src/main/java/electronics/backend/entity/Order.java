package electronics.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="order_table")
public class Order implements Serializable {
    public Order(){}

    public Order(Double sum, User user, String status, LocalDateTime registration) {
        this.sum = sum;
        this.user = user;
        this.status = status;
        this.registration = registration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private LocalDateTime registration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private List<OrderDetail> details = new ArrayList<>();

    @Column
    private Double sum;

    public void addOrderDetail(OrderDetail orderDetail) {
        details.add(orderDetail);
        orderDetail.setOrder(this);
    }
}
