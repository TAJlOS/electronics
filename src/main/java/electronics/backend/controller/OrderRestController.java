package electronics.backend.controller;

import electronics.backend.dto.order.OrderRequestData;
import electronics.backend.dto.order.UserOrdersResponse;
import electronics.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/api/order")
    public ResponseEntity<?> createNewOrder(HttpServletRequest request,
                                            @RequestBody OrderRequestData orderRequestData){
        if(orderService.createUserOrder(orderRequestData, request.getUserPrincipal().getName()) != null){
            return ResponseEntity.ok("Order was successful created");
        }else{
            throw new NullPointerException();
        }
    }

    @PostMapping(value = "/api/user/orders")
    public ResponseEntity<?> getUserOrders(HttpServletRequest request){
        List<UserOrdersResponse> response = orderService.getUserOrders(request.getUserPrincipal().getName());
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            throw new NullPointerException();
        }
    }
}
