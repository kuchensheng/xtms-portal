package com.xtms.application.controller;

import com.mermaid.framework.mvc.APIResponse;
import com.xtms.application.api.OrderCloudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Desription:
 *
 * @author:Hui CreateDate:2018/8/25 14:27
 * version 1.0
 */
@RestController
@Api(value = "订单管理中心",tags = "西塘民宿门户接口")
public class OrderController {

    @Autowired
    OrderCloudService orderCloudService;

    @ApiOperation("订单查询")
    @RequestMapping(value = "/app/order/search",method = RequestMethod.GET)
    public APIResponse<List<Map<String,Object>>> getOrderList(
            @ApiParam(required = true,name = "orderName",value = "订单关键字") @RequestParam(value = "orderName") String orderName
    ) {
        return orderCloudService.getOrderList(orderName);
    }

}
