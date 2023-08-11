package com.study.compicafe.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDTO {

    private int orderCode;
    private String orderDate;
    private String orderTime;
    private String orderMenuName;
    private int totalOrderPrice;

}
