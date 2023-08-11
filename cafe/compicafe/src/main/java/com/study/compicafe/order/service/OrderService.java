package com.study.compicafe.order.service;

import com.study.compicafe.order.entity.Order;
import com.study.compicafe.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    //생성자 초기화
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order findOrderByCode(int orderCode){
        Order order = orderRepository.findById(orderCode);
        return order;
    }
    public List<Order> findAllOrder(){
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    @Transactional
    //조회
    public int registName(Order order){
        Order result = orderRepository.save(order);
        System.out.println(result);

        //result 오브젝트 값이 널이면 0을 넣고 , 아니면 1을 넣는다.
        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
}
