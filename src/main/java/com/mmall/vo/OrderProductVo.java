package com.mmall.vo;

import com.mmall.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yqn19 on 2017-10-07.
 */
public class OrderProductVo {
    private List<OrderItemVo> orderItemList;
    private BigDecimal productTotalPrice;
    private String imageHost;

    public List<OrderItemVo> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemVo> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }
}
