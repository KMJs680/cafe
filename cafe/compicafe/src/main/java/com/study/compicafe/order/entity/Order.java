package com.study.compicafe.order.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Table(name = "tbl_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @Column(name = "order_code")
    private int orderCode;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "order_time")
    private String orderTime;

    private String orderMenuName;
    @Column(name = "total_order_price")
    private int totalOrderPrice;

}
