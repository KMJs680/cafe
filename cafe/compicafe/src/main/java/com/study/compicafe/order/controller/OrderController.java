package com.study.compicafe.order.controller;

import com.study.compicafe.order.dto.OrderDTO;
import com.study.compicafe.order.entity.Order;
import com.study.compicafe.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
    @RequestMapping("/order")
    public class OrderController {
        private final OrderService orderService;

        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }
        @GetMapping("/{orderCode}")
        public ResponseEntity<Object> findMenyByCode(@PathVariable int orderCode) {
            Order order = orderService.findOrderByCode(orderCode);
            if (Objects.isNull(order)) {
                return ResponseEntity.status(404).body(new String("똑바로 입력하시오"));
            }
            return ResponseEntity.ok().body(order);
        }

        @GetMapping("/list")
        public ResponseEntity<List<?>> findAllOrder() {
            List<Order> orderList = orderService.findAllOrder();
            if(orderList.size() <= 0) {
                List<String> error = new ArrayList<>();
                error.add("String");
                System.out.println("반응상태 : " + orderList);
                return  ResponseEntity.status(404).body(error);

            }
            return ResponseEntity.ok().body(orderList);

        }


}

