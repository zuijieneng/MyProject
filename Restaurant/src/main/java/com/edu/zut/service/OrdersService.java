package com.edu.zut.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.zut.entity.Orders;
import com.edu.zut.entity.common.R;

public interface OrdersService extends IService<Orders> {
    //生成订单的时候，要操作两个表：order、order_detail
    public void saveWithDetail(Orders orders);
    //获取订单扩展类明细
    public R<Page> getWithDetail(int page, int pageSize,Long userId);

}
