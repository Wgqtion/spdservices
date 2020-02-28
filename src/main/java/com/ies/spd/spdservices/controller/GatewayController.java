package com.ies.spd.spdservices.controller;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.entity.Gateway;
import com.ies.spd.spdservices.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-12-18
 */
@RestController
@RequestMapping(value = "/gateway")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @RequestMapping(value = "gatewayList")
    public List<Gateway> getGatewayList() throws Exception{
        System.out.println(JSON.toJSONString(gatewayService.findAll()));
        return gatewayService.findAll();
    }

    @RequestMapping(value = "isOnline")
    public List<Gateway> isOnline() throws Exception{
        System.out.println(JSON.toJSONString(gatewayService.findAll()));
        return gatewayService.findAll();
    }
}
