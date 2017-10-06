package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

/**
 * Created by yqn19 on 2017-10-06.
 */
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
    ServerResponse aliCallback(Map<String, String> params);
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);
}
