package com.edu.zut.entity.dto;

import com.edu.zut.entity.OrderDetail;
import com.edu.zut.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * 扩展类：存放一对多关系中的多，主要是订单中多个菜品或套参与主订单关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto extends Orders {
    private List<OrderDetail> orderDetails; //存放订单明细，orderDetails集合：存放订单明细（有哪些菜，菜有哪些口味）
    private int sumNum; //订单内商品总个数
}
