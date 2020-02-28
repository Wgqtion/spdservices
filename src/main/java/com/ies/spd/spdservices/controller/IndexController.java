package com.ies.spd.spdservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Athor: 吴广庆
 * @Date: 2019-08-07
 */
@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String index(){
        return "/index.html";
    }
}
