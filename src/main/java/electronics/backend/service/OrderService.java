package electronics.backend.service;

import electronics.backend.dto.order.OrderDetailRequestData;
import electronics.backend.dto.order.OrderRequestData;
import electronics.backend.dto.order.OrderStatus;
import electronics.backend.dto.order.UserOrdersResponse;
import electronics.backend.entity.CPU;
import electronics.backend.entity.Order;
import electronics.backend.entity.OrderDetail;
import electronics.backend.entity.User;
import electronics.backend.exception.ProductNotFoundException;
import electronics.backend.repository.CPURepository;
import electronics.backend.repository.OrderRepository;
import electronics.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CPURepository cpuRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createUserOrder(OrderRequestData orderRequestData, String username){
        User user = userRepository.findByUsername(username);

        Order order = new Order(orderRequestData.getSum(), user, OrderStatus.NOT_PAID.getStatus(), LocalDateTime.now());

        for(OrderDetailRequestData detail: orderRequestData.getDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setAmount(detail.getCnt());
            Optional<CPU> optionalCPU = cpuRepository.findById(detail.getId());
            if(optionalCPU.isPresent()){
                orderDetail.setCpu(optionalCPU.get());
                order.addOrderDetail(orderDetail);
            }else{
                throw new ProductNotFoundException(detail.getId());
            }
        }
        return orderRepository.save(order);
    }

    public List<UserOrdersResponse> getUserOrders(String username){
        User user = userRepository.findByUsername(username);
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        return orders.stream()
                .map(UserOrdersResponse::new)
                .collect(Collectors.toList());
    }

}
